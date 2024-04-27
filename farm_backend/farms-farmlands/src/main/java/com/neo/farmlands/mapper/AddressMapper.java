package com.neo.farmlands.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neo.farmlands.domain.entity.Address;


import java.util.List;

/**
 * 【请填写功能名称】Mapper接口
 *
 * @author sjm
 */
public interface AddressMapper extends BaseMapper<Address> {
    /**
     * 查询【请填写功能名称】列表
     *
     * @param address 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    List<Address> selectByEntity(Address address);

}
