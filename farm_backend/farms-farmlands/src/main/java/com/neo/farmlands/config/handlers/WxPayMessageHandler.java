package com.neo.farmlands.config.handlers;


import com.egzosn.pay.common.api.PayMessageHandler;
import com.egzosn.pay.common.api.PayService;
import com.egzosn.pay.common.bean.PayOutMessage;
import com.egzosn.pay.common.exception.PayErrorException;
import com.egzosn.pay.wx.v3.bean.response.WxPayMessage;
import com.egzosn.pay.wx.v3.bean.response.order.TradeState;
import com.neo.farmlands.service.impl.H5OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 微信支付回调处理器
 * Created by ZaoSheng on 2016/6/1.
 */
@Component
@Slf4j
public class WxPayMessageHandler implements PayMessageHandler<WxPayMessage, PayService> {


    @Resource
    private H5OrderService h5OrderService;

    @Override
    public PayOutMessage handle(WxPayMessage payMessage, Map<String, Object> context, PayService payService) throws PayErrorException {
        log.info(String.format("【订单支付回调】进入微信支付回调处理器...." ));
        //交易状态
        if (TradeState.SUCCESS.equals(payMessage.getTradeState())){

            /////这里进行成功的处理
            h5OrderService.payCallBack(payMessage);
            return  payService.getPayOutMessage("SUCCESS", "OK");
        }

        return  payService.getPayOutMessage("FAIL", "失败");
    }
}
