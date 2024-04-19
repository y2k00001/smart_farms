package com.neo.farmlands.mapper;

import java.util.List;
import com.neo.farmlands.domain.FarmlandLesseeOrder;

/**
 * 租赁信息和订单关联Mapper接口
 * 
 * @author neo
 * @date 2024-04-19
 */
public interface FarmlandLesseeOrderMapper 
{
    /**
     * 查询租赁信息和订单关联
     * 
     * @param id 租赁信息和订单关联主键
     * @return 租赁信息和订单关联
     */
    public FarmlandLesseeOrder selectFarmlandLesseeOrderById(String id);

    /**
     * 查询租赁信息和订单关联列表
     * 
     * @param farmlandLesseeOrder 租赁信息和订单关联
     * @return 租赁信息和订单关联集合
     */
    public List<FarmlandLesseeOrder> selectFarmlandLesseeOrderList(FarmlandLesseeOrder farmlandLesseeOrder);

    /**
     * 新增租赁信息和订单关联
     * 
     * @param farmlandLesseeOrder 租赁信息和订单关联
     * @return 结果
     */
    public int insertFarmlandLesseeOrder(FarmlandLesseeOrder farmlandLesseeOrder);

    /**
     * 修改租赁信息和订单关联
     * 
     * @param farmlandLesseeOrder 租赁信息和订单关联
     * @return 结果
     */
    public int updateFarmlandLesseeOrder(FarmlandLesseeOrder farmlandLesseeOrder);

    /**
     * 删除租赁信息和订单关联
     * 
     * @param id 租赁信息和订单关联主键
     * @return 结果
     */
    public int deleteFarmlandLesseeOrderById(String id);

    /**
     * 批量删除租赁信息和订单关联
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFarmlandLesseeOrderByIds(String[] ids);
}
