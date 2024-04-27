package com.neo.farmlands.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.neo.farmlands.domain.entity.OrderPay;

/**
 * 账单支付单关联Service接口
 *
 * @author neo
 * @date 2024-04-20
 */
public interface IOrderPayService  extends IService<OrderPay>
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
     * 批量删除账单支付单关联
     *
     * @param ids 需要删除的账单支付单关联主键集合
     * @return 结果
     */
    public int deleteOrderPayByIds(String[] ids);

    /**
     * 删除账单支付单关联信息
     *
     * @param id 账单支付单关联主键
     * @return 结果
     */
    public int deleteOrderPayById(String id);
}
