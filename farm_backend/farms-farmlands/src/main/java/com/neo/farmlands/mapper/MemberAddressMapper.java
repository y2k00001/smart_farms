package com.neo.farmlands.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neo.farmlands.domain.entity.MemberAddress;


import java.util.List;

/**
 * 会员收货地址Mapper接口
 *
 * @author zcc
 */
public interface MemberAddressMapper extends BaseMapper<MemberAddress> {
    /**
     * 查询会员收货地址列表
     *
     * @param memberAddress 会员收货地址
     * @return 会员收货地址集合
     */
    List<MemberAddress> selectByEntity(MemberAddress memberAddress);

    int updateByPrimaryKeySelective(MemberAddress address);

    void updateDefault(int IsDefault, Long id);
}
