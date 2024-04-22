package com.neo.farmlands.constant;
/**
 * @author monkey
 * @date 2024/4/19 16:42
 */

/**
 *@ClassName BusinessConstant
 * TODO
 *@Author neo
 *@date 2024/4/19 16:42
 */
public class BusinessConstant {


    // 支付渠道;10.微信；20支付宝；30现金
    public static final Integer PAY_CHANNEL_WX  = 10;
    public static final Integer PAY_CHANNEL_ALI  = 20;
    public static final Integer PAY_CHANNEL_CASH  = 30;
    public static final String PAY_CHANNEL_WX_CONFIG_KEY  = "payChannelWX";



    // 10 无参数，  20 调起支付插件参数， 30 重定向到指定地址，  40 用户扫码
    public static final String PAY_DATA_TYPE_NONE  = "10";
    public static final String PAY_DATA_TYPE_PREPAY_DATA  = "20";
    public static final String PAY_DATA_TYPE_REDIRECT_URL  = "30";

    public static final String PAY_DATA_TYPE_SCAN_CODE  = "40";
}
