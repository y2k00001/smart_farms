package com.neo.farmlands.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.github.pagehelper.PageHelper;
import com.neo.common.utils.AesCryptoUtils;
import com.neo.farmlands.domain.entity.Member;
import com.neo.farmlands.domain.entity.MemberLogininfor;
import com.neo.farmlands.domain.vo.MemberLogininforQuery;
import com.neo.farmlands.mapper.MemberLogininforMapper;
import com.neo.farmlands.mapper.MemberMapper;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 会员登录记录Service业务层处理
 *
 *
 * @author zcc
 */
@Service
public class MemberLogininforService {
    @Resource
    private MemberLogininforMapper memberLogininforMapper;

    @Resource
    private MemberMapper memberMapper;

    @Value("${aes.key}")
    private String aesKey;

    /**
     * 查询会员登录记录
     *
     * @param id 会员登录记录主键
     * @return 会员登录记录
     */
    public MemberLogininfor selectById(Long id) {
        return memberLogininforMapper.selectById(id);
    }

    /**
     * 查询会员登录记录列表
     *
     * @param query 查询条件
     * @param page 分页条件
     * @return 会员登录记录
     */
    public List<MemberLogininfor> selectList(MemberLogininforQuery query, Pageable page) {
        QueryWrapper<MemberLogininfor> qw = new QueryWrapper<>();
        String phone = query.getPhone();
        if (!StringUtils.isEmpty(phone)) {
            LambdaQueryWrapper<Member> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(Member::getPhoneEncrypted, AesCryptoUtils.encrypt(aesKey, phone));
            Member member = memberMapper.selectOne(wrapper);
            if (member != null){
                qw.eq("phone", member.getPhoneEncrypted());
            }else {
                qw.eq("phone", "-1");
            }
        }
        if (page != null) {
            PageHelper.startPage(page.getPageNumber() + 1, page.getPageSize());
        }
        String ipaddr = query.getIpaddr();
        if (!StringUtils.isEmpty(ipaddr)) {
            qw.eq("ipaddr", ipaddr);
        }
        String loginLocation = query.getLoginLocation();
        if (!StringUtils.isEmpty(loginLocation)) {
            qw.eq("login_location", loginLocation);
        }
        String browser = query.getBrowser();
        if (!StringUtils.isEmpty(browser)) {
            qw.eq("browser", browser);
        }
        String os = query.getOs();
        if (!StringUtils.isEmpty(os)) {
            qw.eq("os", os);
        }
        if (query.getBeginTime() != null && query.getEndTime() != null) {
            qw.ge("login_time", query.getBeginTime());
            qw.lt("login_time", query.getEndTime());
        }
        qw.orderByDesc("login_time");
        return memberLogininforMapper.selectList(qw);
    }

    /**
     * 修改会员登录记录
     *
     * @param memberLogininfor 会员登录记录
     * @return 结果
     */
    public int update(MemberLogininfor memberLogininfor) {
        return memberLogininforMapper.updateById(memberLogininfor);
    }

    /**
     * 删除会员登录记录信息
     *
     * @param id 会员登录记录主键
     * @return 结果
     */
    public int deleteById(Long id) {
        return memberLogininforMapper.deleteById(id);
    }
}
