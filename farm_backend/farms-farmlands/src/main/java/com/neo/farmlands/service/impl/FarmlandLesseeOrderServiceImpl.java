package com.neo.farmlands.service.impl;

import java.util.List;
import com.neo.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.neo.farmlands.mapper.FarmlandLesseeOrderMapper;
import com.neo.farmlands.domain.FarmlandLesseeOrder;
import com.neo.farmlands.service.IFarmlandLesseeOrderService;

/**
 * 租赁信息和订单关联Service业务层处理
 *
 * @author neo
 * @date 2024-04-19
 */
@Service
public class FarmlandLesseeOrderServiceImpl implements IFarmlandLesseeOrderService
{
    @Autowired
    private FarmlandLesseeOrderMapper farmlandLesseeOrderMapper;

    /**
     * 查询租赁信息和订单关联
     *
     * @param id 租赁信息和订单关联主键
     * @return 租赁信息和订单关联
     */
    @Override
    public FarmlandLesseeOrder selectFarmlandLesseeOrderById(String id)
    {
        return farmlandLesseeOrderMapper.selectFarmlandLesseeOrderById(id);
    }

    /**
     * 查询租赁信息和订单关联列表
     *
     * @param farmlandLesseeOrder 租赁信息和订单关联
     * @return 租赁信息和订单关联
     */
    @Override
    public List<FarmlandLesseeOrder> selectFarmlandLesseeOrderList(FarmlandLesseeOrder farmlandLesseeOrder)
    {
        return farmlandLesseeOrderMapper.selectFarmlandLesseeOrderList(farmlandLesseeOrder);
    }

    /**
     * 新增租赁信息和订单关联
     *
     * @param farmlandLesseeOrder 租赁信息和订单关联
     * @return 结果
     */
    @Override
    public int insertFarmlandLesseeOrder(FarmlandLesseeOrder farmlandLesseeOrder)
    {
        farmlandLesseeOrder.setCreateTime(DateUtils.getNowDate());
        return farmlandLesseeOrderMapper.insertFarmlandLesseeOrder(farmlandLesseeOrder);
    }

    /**
     * 修改租赁信息和订单关联
     *
     * @param farmlandLesseeOrder 租赁信息和订单关联
     * @return 结果
     */
    @Override
    public int updateFarmlandLesseeOrder(FarmlandLesseeOrder farmlandLesseeOrder)
    {
        farmlandLesseeOrder.setUpdateTime(DateUtils.getNowDate());
        return farmlandLesseeOrderMapper.updateFarmlandLesseeOrder(farmlandLesseeOrder);
    }

    /**
     * 批量删除租赁信息和订单关联
     *
     * @param ids 需要删除的租赁信息和订单关联主键
     * @return 结果
     */
    @Override
    public int deleteFarmlandLesseeOrderByIds(String[] ids)
    {
        return farmlandLesseeOrderMapper.deleteFarmlandLesseeOrderByIds(ids);
    }

    /**
     * 删除租赁信息和订单关联信息
     *
     * @param id 租赁信息和订单关联主键
     * @return 结果
     */
    @Override
    public int deleteFarmlandLesseeOrderById(String id)
    {
        return farmlandLesseeOrderMapper.deleteFarmlandLesseeOrderById(id);
    }
}
