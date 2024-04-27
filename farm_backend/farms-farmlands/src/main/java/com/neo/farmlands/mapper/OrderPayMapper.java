package com.neo.farmlands.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neo.farmlands.domain.entity.OrderPay;

/**
 * 账单支付单关联Mapper接口
 *
 * @author neo
 * @date 2024-04-20
 */
public interface OrderPayMapper extends BaseMapper<OrderPay>
{
    /**
     * 查询账单支付单关联
     *
     * @param id 账单支付单关联主键
     * @return 账单支付单关联
     */
    public OrderPay selectOrderPayById(String id);

    /**
     * 查询账单支付单关联列表
     *
     * @param orderPay 账单支付单关联
     * @return 账单支付单关联集合
     */
    public List<OrderPay> selectOrderPayList(OrderPay orderPay);

    /**
     * 新增账单支付单关联
     *
     * @param orderPay 账单支付单关联
     * @return 结果
     */
    public int insertOrderPay(OrderPay orderPay);

    /**
     * 修改账单支付单关联
     *
     * @param orderPay 账单支付单关联
     * @return 结果
     */
    public int updateOrderPay(OrderPay orderPay);

    /**
     * 删除账单支付单关联
     *
     * @param id 账单支付单关联主键
     * @return 结果
     */
    public int deleteOrderPayById(String id);

    /**
     * 批量删除账单支付单关联
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteOrderPayByIds(String[] ids);
}
