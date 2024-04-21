package com.neo.farmlands.controller.manage;
/**
 * @author monkey
 * @date 2024/4/10 11:37
 */

import cn.hutool.core.util.IdUtil;
import com.egzosn.pay.spring.boot.core.PayServiceManager;
import com.egzosn.pay.spring.boot.core.bean.MerchantPayOrder;
import com.neo.common.annotation.Anonymous;
import com.neo.common.core.domain.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Map;

/**
 *@ClassName TestController
 * TODO
 *@Author neo
 *@date 2024/4/10 11:37
 */
@RestController
@RequestMapping("/biz/test")
@Anonymous
public class BizTestController {

    @Autowired
    private PayServiceManager manager;
    @GetMapping("/pay2")
    public R pay() {

        return R.ok("pay");
    }


    /**
     * 刷卡付,pos主动扫码付款(条码付)
     *
     * @param detailsId       账户id
     * @param transactionType 交易类型， 这个针对于每一个 支付类型的对应的几种交易方式
     * @param authCode        授权码，条码等
     * @param price           金额
     * @return 支付结果
     */
    @RequestMapping(value = "microPay")
    public Map<String, Object> microPay(String detailsId, String transactionType, BigDecimal price, String authCode) {
        //获取对应的支付账户操作工具（可根据账户id）
        MerchantPayOrder payOrder = new MerchantPayOrder(detailsId, transactionType, "订单title", "摘要", null == price ? new BigDecimal(0.01) : price, IdUtil.fastSimpleUUID());
        //设置授权码，条码等
        payOrder.setAuthCode(authCode);
        return manager.microPay(payOrder);
    }

}
