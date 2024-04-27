package com.neo.farmlands.service.impl;
/**
 * @author monkey
 * @date 2024/4/21 15:56
 */

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSON;
import com.egzosn.pay.spring.boot.core.PayServiceManager;
import com.egzosn.pay.spring.boot.core.bean.MerchantPayOrder;
import com.neo.farmlands.constant.BusinessConstant;
import com.neo.farmlands.domain.entity.FarmlandLesseeOrder;
import com.neo.farmlands.domain.entity.Pay;
import com.neo.farmlands.domain.vo.PayFarmlandLesseeReqVO;
import com.neo.farmlands.domain.vo.UnifiedPayRS;
import com.neo.farmlands.enums.PaySourceEnum;
import com.neo.farmlands.enums.PayStateEnum;
import com.neo.farmlands.service.IThirdPaymentService;
import com.neo.system.service.ISysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Map;

/**
 *@ClassName ThirdPaymentServiceImpl
 * TODO
 *@Author neo
 *@date 2024/4/21 15:56
 */
@Service
public class ThirdPaymentServiceImpl implements IThirdPaymentService {

    @Resource
    private ISysConfigService configService;

    @Resource
    private PayServiceManager manager;
    @Override
    public UnifiedPayRS wxPay(PayFarmlandLesseeReqVO payFarmlandLesseeReqVO, Pay pay, FarmlandLesseeOrder farmlandLesseeOrder) {
        String payChannelWXConfigVal = configService.selectConfigByKey(BusinessConstant.PAY_CHANNEL_WX_CONFIG_KEY);
        //获取对应的支付账户操作工具（可根据账户id）
        String wayTrade = PaySourceEnum.getByValue(pay.getPaySource()).getDescription();
        MerchantPayOrder payOrder = new MerchantPayOrder(payChannelWXConfigVal, wayTrade, pay.getSubject(), pay.getBody(), pay.getPayAmount(), pay.getPayId());

        Map<String, Object> result = manager.app(payOrder);
        UnifiedPayRS unifiedPayRS = new UnifiedPayRS();
        unifiedPayRS.setOrderId(farmlandLesseeOrder.getOrderId());
        unifiedPayRS.setPayId(pay.getPayId());
        unifiedPayRS.setPayState(PayStateEnum.PAY_STATE_PAYING.getCode());
        unifiedPayRS.setPayDataType(BusinessConstant.PAY_DATA_TYPE_PREPAY_DATA);
        unifiedPayRS.setPayData((String) result.get("prepay_id"));
        unifiedPayRS.setErrCode("00");
        unifiedPayRS.setErrMsg("success");
        unifiedPayRS.setChannelRetMsg(JSON.toJSONString(result));
        unifiedPayRS.setPayInfo((String) result.get("prepay_id"));

        return unifiedPayRS;
    }

    @Override
    public UnifiedPayRS aliPay(PayFarmlandLesseeReqVO payFarmlandLesseeReqVO) {
        return null;
    }
}
