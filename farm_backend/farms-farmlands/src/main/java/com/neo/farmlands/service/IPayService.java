package com.neo.farmlands.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.neo.farmlands.domain.entity.FarmlandLessee;
import com.neo.farmlands.domain.entity.Pay;
import com.neo.farmlands.domain.vo.PayFarmlandLesseeReqVO;
import com.neo.farmlands.domain.vo.UnifiedPayRS;

/**
 * 支付记录Service接口
 *
 * @author neo
 * @date 2024-04-20
 */
public interface IPayService extends IService<Pay>
{
    /**
     * 查询支付记录
     *
     * @param id 支付记录主键
     * @return 支付记录
     */
    public Pay selectPayById(String id);

    public Pay getPayByPayId(String id);

    /**
     * 查询支付记录列表
     *
     * @param pay 支付记录
     * @return 支付记录集合
     */
    public List<Pay> selectPayList(Pay pay);

    /**
     * 新增支付记录
     *
     * @param pay 支付记录
     * @return 结果
     */
    public int insertPay(Pay pay);

    /**
     * 修改支付记录
     *
     * @param pay 支付记录
     * @return 结果
     */
    public int updatePay(Pay pay);

    /**
     * 批量删除支付记录
     *
     * @param ids 需要删除的支付记录主键集合
     * @return 结果
     */
    public int deletePayByIds(String[] ids);

    /**
     * 删除支付记录信息
     *
     * @param id 支付记录主键
     * @return 结果
     */
    public int deletePayById(String id);

    UnifiedPayRS prePayFarmlandLessee(PayFarmlandLesseeReqVO payFarmlandLesseeReqVO);

    Pay saveFarmlandLesseePay(PayFarmlandLesseeReqVO payFarmlandLesseeReqVO, FarmlandLessee farmlandLessee);

    void updateStatusByPayId(String payId,Integer status);

    void updateByPayId(String payId, Pay updatePay);
}
