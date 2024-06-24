package com.neo.farmlands.controller.api;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;

import com.egzosn.pay.common.api.PayMessageInterceptor;
import com.egzosn.pay.common.api.PayService;
import com.egzosn.pay.spring.boot.core.PayServiceManager;
import com.egzosn.pay.spring.boot.core.merchant.MerchantDetailsService;
import com.egzosn.pay.spring.boot.core.merchant.PaymentPlatformMerchantDetails;
import com.egzosn.pay.web.support.HttpRequestNoticeParams;
import com.neo.farmlands.config.handlers.WxPayMessageHandler;
import com.neo.farmlands.service.impl.H5OrderService;
import io.swagger.annotations.Api;
import org.apache.ibatis.transaction.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * 订单表Controller
 *
 * @author sjm
 * @date 2023-04-05
 */
@Api(description ="微信回调接口列表")
@RestController
@RequestMapping("/no-auth/wechat")
public class PayNotifyController {
    private static final Logger log = LoggerFactory.getLogger(PayNotifyController.class);

    @Autowired
    private H5OrderService h5OrderService;

    @Autowired
    private PayServiceManager manager;
    @Autowired
    private MerchantDetailsService<PaymentPlatformMerchantDetails> detailsService;

    @Autowired
    private WxPayMessageHandler wxPayMessageHandler;


    private final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    // /**
    //  * 微信支付回调
    //  * @param request
    //  * @throws IOException
    //  */
    // @PostMapping("/notify")
    // public void weChatPayNotify(HttpServletRequest request) throws Exception {
    //     log.info("收到了微信支付回调");
    //     // 从请求头中获取信息
    //     String timestamp  = request.getHeader("Wechatpay-Timestamp");
    //     String nonce = request.getHeader("Wechatpay-Nonce");
    //     String signature = request.getHeader("Wechatpay-Signature");
    //     String singType = request.getHeader("Wechatpay-Signature-Type");
    //     String wechatPayCertificateSerialNumber = request.getHeader("Wechatpay-Serial");
    //     // 拿到请求体body
    //     StringBuilder requestBody = new StringBuilder();
    //     String line;
    //     BufferedReader reader;
    //     reader = request.getReader();
    //     while (null != (line = reader.readLine())) {
    //         requestBody.append(line);
    //     }
    //     // 构造 RequestParam
    //     RequestParam requestParam = new RequestParam.Builder()
    //             .serialNumber(wechatPayCertificateSerialNumber)
    //             .nonce(nonce)
    //             .signature(signature)
    //             .timestamp(timestamp)
    //             .body(requestBody.toString())
    //             .build();
    //     log.info("【requestParam】" + JSONObject.toJSON(requestParam));
    //     //初始化了 RSAAutoCertificateConfig
    //     Config config = WechatPayConfig.getInstance();
    //     // 初始化解析器 NotificationParser
    //     NotificationParser parser = new NotificationParser((NotificationConfig) config);
    //     // 以支付通知回调为例，验签、解密并转换成 Transaction
    //     Transaction transaction = parser.parse(requestParam, Transaction.class);
    //     log.info("【transaction】" + JSONObject.toJSON(transaction));
    //     PayNotifyMessageDTO message = new PayNotifyMessageDTO();
    //     message.setOutTradeNo(Long.valueOf(transaction.getOutTradeNo()));
    //     message.setMemberId(Long.valueOf(transaction.getAttach()));
    //     message.setTradeStatus(transaction.getTradeState());
    //     if (StrUtil.isEmpty(transaction.getSuccessTime())){
    //         throw new RuntimeException("微信支付回调失败");
    //     }
    //     message.setPayTime(formatter.parse(transaction.getSuccessTime().substring(0, transaction.getSuccessTime().indexOf("+"))));
    //     message.setTradeNo(transaction.getTransactionId());
    //     h5OrderService.payCallBack(message);
    // }

    /**
     * 支付回调地址
     *
     * @param request   请求
     * @param detailsId 列表id
     * @return 支付是否成功
     * 拦截器相关增加， 详情查看{@link com.egzosn.pay.common.api.PayService#addPayMessageInterceptor(PayMessageInterceptor)}
     * <p>
     * 业务处理在对应的PayMessageHandler里面处理，在哪里设置PayMessageHandler，详情查看{@link com.egzosn.pay.common.api.PayService#setPayMessageHandler(com.egzosn.pay.common.api.PayMessageHandler)}
     * </p>
     * 如果未设置 {@link com.egzosn.pay.common.api.PayMessageHandler} 那么会使用默认的 {@link com.egzosn.pay.common.api.DefaultPayMessageHandler}
     */
    @RequestMapping(value = "payBack/{detailsId}")
    public String payBack(HttpServletRequest request, @PathVariable String detailsId) {
        //业务处理在对应的PayMessageHandler里面处理，在哪里设置PayMessageHandler，详情查看com.egzosn.pay.common.api.PayService.setPayMessageHandler()


        PaymentPlatformMerchantDetails details = (PaymentPlatformMerchantDetails)this.detailsService.loadMerchantByMerchantId(detailsId);
        PayService payService = details.getPayService();
        payService.setPayMessageHandler(wxPayMessageHandler);
        return payService.payBack(new HttpRequestNoticeParams(request)).toMessage();
        // return manager.payBack(detailsId, new HttpRequestNoticeParams(request));
    }
}
