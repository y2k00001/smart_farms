package com.neo.farmlands.domain.vo.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("订单支付请求体")
public class OrderPayForm {
    @ApiModelProperty(value = "订单id", required = true)
    private String orderId;

    @ApiModelProperty(value = "支付方式： 1-支付宝 2-微信（默认）", required = true)
    private Integer type;

    @ApiModelProperty(value = "用户id", hidden = true)
    private Long memberId;

    @ApiModelProperty(value = "微信支付方式 1：公众号  2：小程序")
    private Integer wechatType = 1;
}
