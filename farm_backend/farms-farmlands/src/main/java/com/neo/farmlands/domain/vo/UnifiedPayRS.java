package com.neo.farmlands.domain.vo;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 创建订单(统一订单) 响应参数
 * @author neo
 * @datetime  2023/2/10 16:04
 * @return {@link null}
 **/
@Data
@ApiModel("创建订单(统一订单) 响应参数")
public class UnifiedPayRS {

    /** 业务系统订单号 **/
    @ApiModelProperty("=业务系统订单号")
    private String orderId;

    /** 支付系统支付单号 **/
    @ApiModelProperty("支付系统支付单号")
    private String payId;

    /**
     * 第三方订单号
     */
    @ApiModelProperty("第三方订单交易号(支付成功返回)")
    private String thirdTradeNo;



    /** 支付状态;00,待支付；10，支付中；20，支付成功；30-支付失败, 40-已撤销, 50-已退款, 60-订单关闭 **/
    @ApiModelProperty("支付状态;00,待支付；10，支付中；20，支付成功；30-支付失败, 40-已撤销, 50-已退款, 60-订单关闭")
    private Integer payState;

    /** 支付参数类型  ( 无参数，  调起支付插件参数， 重定向到指定地址，  用户扫码   )   **/
    @ApiModelProperty("BusinessConstant.PAY_DATA_TYPE_XXX 支付参数类型  ( 10 无参数，  20 调起支付插件参数， 30 重定向到指定地址，  40 用户扫码   ) ")
    private String payDataType;

    /** 支付参数 **/
    @ApiModelProperty("支付参数")
    private String payData;

    /** 第三方支付渠道返回错误代码 **/
    @ApiModelProperty("渠道返回错误代码")
    private String errCode;

    /** 第三方支付渠道返回错误信息 **/
    @ApiModelProperty("渠道返回错误信息")
    private String errMsg;

    /** 第三方支付渠道返回数据包 (无需JSON序列化) **/
    @ApiModelProperty("第三方支付渠道返回数据包 (无需JSON序列化")
    @JSONField(serialize = false)
    private String  channelRetMsg;

    /** 预支付数据包 **/
    @ApiModelProperty("预支付数据包,微信拉起收银台的参数")
    private String payInfo;


}
