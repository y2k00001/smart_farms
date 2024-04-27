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


    public static final String MEMBER_INFO = "member_info";

    /**
     * 会员账号状态
     */
    public static class MEMBER_ACCOUNT_STATUS {
        public static final Integer FORBIDDEN = 0;
        public static final Integer NORMAL = 1;


    }

    /**
     * 登录提示信息
     */
    public static class LOGIN_INFO {
        public static final String WRONG = "账号或密码错误";
        public static final String FORBIDDEN = "您的账号被禁用，请联系管理员";
        public static final String SUCCESS = "登录成功";
        public static final String TO_REGISTER = "请先注册";
    }


    /**
     * 验证码相关提示信息
     */
    public static class VERIFY_CODE_INFO {
        public static final String EXPIRED = "验证码已过期";
        public static final String WRONG = "验证码错误";
    }

    /**
     * 上架状态：0->下架；1->上架
     */
    public static class PublishStatus {
        public static final Integer GROUNDING = 1;
        public static final Integer UNDERCARRIAGE = 0;
    }
}
