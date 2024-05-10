package com.neo.farmlands.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.neo.common.utils.AesCryptoUtils;
import com.neo.common.utils.PhoneUtils;
import com.neo.farmlands.constant.BusinessConstant;
import com.neo.farmlands.domain.entity.Member;
import com.neo.farmlands.domain.entity.MemberAddress;
import com.neo.farmlands.domain.vo.MemberAddressForm;
import com.neo.farmlands.domain.vo.MemberAddressVO;
import com.neo.farmlands.mapper.MemberAddressMapper;
import com.neo.framework.config.LocalDataUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 会员收货地址Service业务层处理
 *
 * @author sjm
 */
@Service
@Transactional
public class H5MemberAddressService {
    @Autowired
    private MemberAddressMapper memberAddressMapper;

    @Value("${aes.key}")
    private String aesKey;

    /**
     * 查询会员收货地址
     *
     * @param id 会员收货地址主键
     * @return 会员收货地址
     */

    public MemberAddressVO selectById(Long id) {
        MemberAddress memberAddress = memberAddressMapper.selectById(id);
        MemberAddressVO memberAddressVO = new MemberAddressVO();
        BeanUtils.copyProperties(memberAddress, memberAddressVO);
        memberAddressVO.setPhone(AesCryptoUtils.decrypt(aesKey, memberAddress.getPhoneEncrypted()));
        return memberAddressVO;
    }

    public List<MemberAddressVO> selectList() {
        Member member = (Member) LocalDataUtil.getVar(BusinessConstant.MEMBER_INFO);
        MemberAddress memberAddress = new MemberAddress();
        memberAddress.setMemberId(member.getId());
        List<MemberAddress> memberAddressesList = memberAddressMapper.selectByEntity(memberAddress);
        return  memberAddressesList.stream().map(it -> {
            MemberAddressVO vo = new MemberAddressVO();
            BeanUtils.copyProperties(it, vo);
            vo.setPhone(AesCryptoUtils.decrypt(aesKey, it.getPhoneEncrypted()));
            return vo;
        }).collect(Collectors.toList());
    }

    /**
     * 新增会员收货地址
     *
     * @param memberAddressForm 会员收货地址
     * @return 结果
     */
    public int insert(MemberAddressForm memberAddressForm) {
        Member member = (Member) LocalDataUtil.getVar(BusinessConstant.MEMBER_INFO);
        if (memberAddressForm.getIsDefault() == 1) {
            //将别的设置为0
            memberAddressMapper.updateDefault(0,member.getId());
        }
        MemberAddress memberAddress = new MemberAddress();
        BeanUtils.copyProperties(memberAddressForm, memberAddress);
        memberAddress.setPhoneHidden(PhoneUtils.hidePhone(memberAddressForm.getPhone()));
        memberAddress.setPhoneEncrypted(AesCryptoUtils.encrypt(aesKey, memberAddressForm.getPhone()));
        memberAddress.setMemberId(member.getId());
        memberAddress.setCreateTime(new Date());
        return memberAddressMapper.insert(memberAddress);
    }

    /**
     * 修改会员收货地址
     *
     * @param memberAddressForm 会员收货地址
     * @return 结果
     */

    public int update(MemberAddressForm memberAddressForm) {
        Member member = (Member) LocalDataUtil.getVar(BusinessConstant.MEMBER_INFO);
        if (memberAddressForm.getIsDefault() == 1) {
            //将别的设置为0
            memberAddressMapper.updateDefault(0,member.getId());
        }
        MemberAddress memberAddress = new MemberAddress();
        BeanUtils.copyProperties(memberAddressForm, memberAddress);
        memberAddress.setPhoneHidden(PhoneUtils.hidePhone(memberAddressForm.getPhone()));
        memberAddress.setPhoneEncrypted(AesCryptoUtils.encrypt(aesKey, memberAddressForm.getPhone()));
        memberAddress.setUpdateTime(new Date());
        memberAddress.setUpdateBy(member.getId().toString());
        return memberAddressMapper.updateById(memberAddress);
    }

    /**
     * 批量删除会员收货地址
     *
     * @param ids 需要删除的会员收货地址主键
     * @return 结果
     */

    public int deleteByIds(Long[] ids) {
        return memberAddressMapper.deleteBatchIds(Arrays.asList(ids));
    }

    /**
     * 删除会员收货地址信息
     *
     * @param id 会员收货地址主键
     * @return 结果
     */

    public int deleteById(Long id) {
        return memberAddressMapper.deleteById(id);
    }

    public MemberAddressVO getDefault() {
        Member member = (Member) LocalDataUtil.getVar(BusinessConstant.MEMBER_INFO);
        QueryWrapper<MemberAddress> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("member_id",member.getId());
        queryWrapper.eq("is_default",1);
        List<MemberAddress> list = memberAddressMapper.selectList(queryWrapper);
        if (CollectionUtils.isEmpty(list)){
            return null;
        }
        MemberAddressVO memberAddressVO = new MemberAddressVO();
        BeanUtils.copyProperties(list.get(0), memberAddressVO);
        memberAddressVO.setPhone(AesCryptoUtils.decrypt(aesKey, list.get(0).getPhoneEncrypted()));
        return memberAddressVO;
    }
}
