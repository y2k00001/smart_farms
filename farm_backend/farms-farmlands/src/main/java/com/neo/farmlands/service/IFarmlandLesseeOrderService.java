package com.neo.farmlands.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.neo.farmlands.domain.entity.FarmlandLessee;
import com.neo.farmlands.domain.entity.FarmlandLesseeOrder;
import com.neo.farmlands.domain.entity.Pay;
import com.neo.farmlands.domain.vo.PayFarmlandLesseeReqVO;

import java.util.List;

/**
* @author monkey
* @description 针对表【t_farmland_lessee_order(租赁信息和订单关联表)】的数据库操作Service
* @createDate 2024-04-20 23:07:12
*/
public interface IFarmlandLesseeOrderService extends IService<FarmlandLesseeOrder> {
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
     * 批量删除租赁信息和订单关联
     *
     * @param ids 需要删除的租赁信息和订单关联主键集合
     * @return 结果
     */
    public int deleteFarmlandLesseeOrderByIds(String[] ids);

    /**
     * 删除租赁信息和订单关联信息
     *
     * @param id 租赁信息和订单关联主键
     * @return 结果
     */
    public int deleteFarmlandLesseeOrderById(String id);

    FarmlandLesseeOrder saveBizOrder(PayFarmlandLesseeReqVO payFarmlandLesseeReqVO, Pay pay);

    FarmlandLessee getOneByOrderId(Long orderId);

    FarmlandLesseeOrder getOneByFarmlandLesseeId(String farmlandLesseeId, boolean isThorwnException);
}
