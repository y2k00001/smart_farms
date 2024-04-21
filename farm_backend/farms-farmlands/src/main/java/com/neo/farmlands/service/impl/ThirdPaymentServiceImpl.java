package com.neo.farmlands.service.impl;
/**
 * @author monkey
 * @date 2024/4/21 15:56
 */

import cn.hutool.core.util.IdUtil;
import com.egzosn.pay.spring.boot.core.PayServiceManager;
import com.egzosn.pay.spring.boot.core.bean.MerchantPayOrder;
import com.neo.farmlands.constant.BusinessConstant;
import com.neo.farmlands.domain.Pay;
import com.neo.farmlands.domain.vo.PayFarmlandLesseeReqVO;
import com.neo.farmlands.domain.vo.UnifiedPayRS;
import com.neo.farmlands.enums.PaySourceEnum;
import com.neo.farmlands.service.IThirdPaymentService;
import com.neo.system.service.ISysConfigService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Map;

/**
 *@ClassName ThirdPaymentServiceImpl
 * TODO
 *@Author neo
 *@date 2024/4/21 15:56
 */
public class ThirdPaymentServiceImpl implements IThirdPaymentService {

    @Resource
    private ISysConfigService configService;

    @Resource
    private PayServiceManager manager;
    @Override
    public UnifiedPayRS wxPay(PayFarmlandLesseeReqVO payFarmlandLesseeReqVO, Pay pay) {
        String payChannelWXConfigVal = configService.selectConfigByKey(BusinessConstant.PAY_CHANNEL_WX_CONFIG_KEY);
        //获取对应的支付账户操作工具（可根据账户id）
        String wayTrade = PaySourceEnum.getByValue(pay.getPaySource()).getDescription();
        MerchantPayOrder payOrder = new MerchantPayOrder(payChannelWXConfigVal, wayTrade, pay.getSubject(), pay.getBody(), pay.getPayAmount(), pay.getPayId());

        Map<String, Object> result = manager.app(payOrder);
        return null;
    }

    @Override
    public UnifiedPayRS aliPay(PayFarmlandLesseeReqVO payFarmlandLesseeReqVO) {
        return null;
    }
}
