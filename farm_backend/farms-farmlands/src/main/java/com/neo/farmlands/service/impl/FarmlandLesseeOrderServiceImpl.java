package com.neo.farmlands.service.impl;
import java.util.Date;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neo.common.utils.DateUtils;

import com.neo.farmlands.constant.IDConstants;
import com.neo.farmlands.domain.entity.FarmlandLesseeOrder;
import com.neo.farmlands.domain.entity.OrderPay;
import com.neo.farmlands.domain.entity.Pay;
import com.neo.farmlands.domain.vo.PayFarmlandLesseeReqVO;
import com.neo.farmlands.enums.OrderTypeEnum;
import com.neo.farmlands.mapper.FarmlandLesseeOrderMapper;
import com.neo.farmlands.service.IFarmlandLesseeOrderService;
import com.neo.farmlands.service.IOrderPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author monkey
* @description 针对表【t_farmland_lessee_order(租赁信息和订单关联表)】的数据库操作Service实现
* @createDate 2024-04-20 23:07:12
*/
@Service
public class FarmlandLesseeOrderServiceImpl extends ServiceImpl<FarmlandLesseeOrderMapper, FarmlandLesseeOrder>
    implements IFarmlandLesseeOrderService {
    @Autowired
    private FarmlandLesseeOrderMapper farmlandLesseeOrderMapper;

    @Resource
    private IOrderPayService orderPayService;

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

    @Override
    public FarmlandLesseeOrder saveBizOrder(PayFarmlandLesseeReqVO payFarmlandLesseeReqVO, Pay pay) {
        FarmlandLesseeOrder farmlandLesseeOrder = new FarmlandLesseeOrder();
        farmlandLesseeOrder.setFarmlandLesseeId(payFarmlandLesseeReqVO.getFarmlandLesseeId());
        farmlandLesseeOrder.setOrderId(IDConstants.FARMLAND_LESSEE_ORDER_ID_PREFIX+ IdUtil.fastSimpleUUID());

        this.save(farmlandLesseeOrder);

        OrderPay orderPay = new OrderPay();
        orderPay.setOrderId(farmlandLesseeOrder.getOrderId());
        orderPay.setOrderType(OrderTypeEnum.ORDER_TYPE_FARMLAND_LESSEE.getCode());
        orderPay.setPayId(pay.getPayId());

        orderPayService.save(orderPay);

        return farmlandLesseeOrder;

    }
}




