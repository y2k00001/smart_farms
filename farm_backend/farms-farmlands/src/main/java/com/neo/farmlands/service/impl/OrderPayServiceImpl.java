package com.neo.farmlands.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neo.common.utils.DateUtils;
import com.neo.farmlands.domain.vo.PayVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.neo.farmlands.mapper.OrderPayMapper;
import com.neo.farmlands.domain.entity.OrderPay;
import com.neo.farmlands.service.IOrderPayService;

/**
 * 账单支付单关联Service业务层处理
 *
 * @author neo
 * @date 2024-04-20
 */
@Service
public class OrderPayServiceImpl extends ServiceImpl<OrderPayMapper, OrderPay> implements IOrderPayService
{
    @Autowired
    private OrderPayMapper orderPayMapper;

    /**
     * 查询账单支付单关联
     *
     * @param id 账单支付单关联主键
     * @return 账单支付单关联
     */
    @Override
    public OrderPay selectOrderPayById(String id)
    {
        return orderPayMapper.selectOrderPayById(id);
    }

    /**
     * 查询账单支付单关联列表
     *
     * @param orderPay 账单支付单关联
     * @return 账单支付单关联
     */
    @Override
    public List<OrderPay> selectOrderPayList(OrderPay orderPay)
    {
        return orderPayMapper.selectOrderPayList(orderPay);
    }

    /**
     * 新增账单支付单关联
     *
     * @param orderPay 账单支付单关联
     * @return 结果
     */
    @Override
    public int insertOrderPay(OrderPay orderPay)
    {
        orderPay.setCreateTime(DateUtils.getNowDate());
        return orderPayMapper.insertOrderPay(orderPay);
    }

    /**
     * 修改账单支付单关联
     *
     * @param orderPay 账单支付单关联
     * @return 结果
     */
    @Override
    public int updateOrderPay(OrderPay orderPay)
    {
        orderPay.setUpdateTime(DateUtils.getNowDate());
        return orderPayMapper.updateOrderPay(orderPay);
    }

    /**
     * 批量删除账单支付单关联
     *
     * @param ids 需要删除的账单支付单关联主键
     * @return 结果
     */
    @Override
    public int deleteOrderPayByIds(String[] ids)
    {
        return orderPayMapper.deleteOrderPayByIds(ids);
    }

    /**
     * 删除账单支付单关联信息
     *
     * @param id 账单支付单关联主键
     * @return 结果
     */
    @Override
    public int deleteOrderPayById(String id)
    {
        return orderPayMapper.deleteOrderPayById(id);
    }

    @Override
    public PayVO getByOrderId(Long orderId) {
        return orderPayMapper.getByOrderId(orderId);
    }
}
