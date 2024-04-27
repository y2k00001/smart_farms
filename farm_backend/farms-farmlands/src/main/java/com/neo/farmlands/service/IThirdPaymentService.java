package com.neo.farmlands.service;
/**
 * @author monkey
 * @date 2024/4/21 15:55
 */

import com.neo.farmlands.domain.entity.FarmlandLesseeOrder;
import com.neo.farmlands.domain.entity.Pay;
import com.neo.farmlands.domain.vo.PayFarmlandLesseeReqVO;
import com.neo.farmlands.domain.vo.UnifiedPayRS;

/**
 * 第三方支付服务
 * TODO
 *@Author NEO
 *@date 2024/4/21 15:55
 */
public interface IThirdPaymentService {
    UnifiedPayRS wxPay(PayFarmlandLesseeReqVO payFarmlandLesseeReqVO, Pay pay, FarmlandLesseeOrder farmlandLesseeOrder);

    UnifiedPayRS aliPay(PayFarmlandLesseeReqVO payFarmlandLesseeReqVO);
}
