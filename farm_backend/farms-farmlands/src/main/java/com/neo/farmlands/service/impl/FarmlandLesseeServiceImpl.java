package com.neo.farmlands.service.impl;

import java.util.List;
import com.neo.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.neo.farmlands.mapper.FarmlandLesseeMapper;
import com.neo.farmlands.domain.FarmlandLessee;
import com.neo.farmlands.service.IFarmlandLesseeService;

/**
 * 农田租赁信息Service业务层处理
 *
 * @author neo
 * @date 2024-04-15
 */
@Service
public class FarmlandLesseeServiceImpl implements IFarmlandLesseeService
{
    @Autowired
    private FarmlandLesseeMapper farmlandLesseeMapper;

    /**
     * 查询农田租赁信息
     *
     * @param id 农田租赁信息主键
     * @return 农田租赁信息
     */
    @Override
    public FarmlandLessee selectFarmlandLesseeById(String id)
    {
        return farmlandLesseeMapper.selectFarmlandLesseeById(id);
    }

    /**
     * 查询农田租赁信息列表
     *
     * @param farmlandLessee 农田租赁信息
     * @return 农田租赁信息
     */
    @Override
    public List<FarmlandLessee> selectFarmlandLesseeList(FarmlandLessee farmlandLessee)
    {
        return farmlandLesseeMapper.selectFarmlandLesseeList(farmlandLessee);
    }

    /**
     * 新增农田租赁信息
     *
     * @param farmlandLessee 农田租赁信息
     * @return 结果
     */
    @Override
    public int insertFarmlandLessee(FarmlandLessee farmlandLessee)
    {
        farmlandLessee.setCreateTime(DateUtils.getNowDate());
        return farmlandLesseeMapper.insertFarmlandLessee(farmlandLessee);
    }

    /**
     * 修改农田租赁信息
     *
     * @param farmlandLessee 农田租赁信息
     * @return 结果
     */
    @Override
    public int updateFarmlandLessee(FarmlandLessee farmlandLessee)
    {
        farmlandLessee.setUpdateTime(DateUtils.getNowDate());
        return farmlandLesseeMapper.updateFarmlandLessee(farmlandLessee);
    }

    /**
     * 批量删除农田租赁信息
     *
     * @param ids 需要删除的农田租赁信息主键
     * @return 结果
     */
    @Override
    public int deleteFarmlandLesseeByIds(String[] ids)
    {
        return farmlandLesseeMapper.deleteFarmlandLesseeByIds(ids);
    }

    /**
     * 删除农田租赁信息信息
     *
     * @param id 农田租赁信息主键
     * @return 结果
     */
    @Override
    public int deleteFarmlandLesseeById(String id)
    {
        return farmlandLesseeMapper.deleteFarmlandLesseeById(id);
    }
}
