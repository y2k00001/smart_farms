package com.neo.farmlands.enums;
/**
 * @author monkey
 * @date 2024/4/21 15:35
 */

/**
 *@ClassName PayChannelEnum
 * TODO
 *@Author neo
 *@date 2024/4/21 15:35
 */
public enum PayChannelEnum {
    PAY_CHANNEL_WXPAY(10, "微信支付"),

    PAY_CHANNEL_ALIPAY(20, "支付宝支付"),
    PAY_CHANNEL_CASH(30, "线下支付"),
            ;



    private final Integer code;
    private final String description;

    PayChannelEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static PayChannelEnum getByValue(Integer code){
        for (PayChannelEnum constants : values()
        ) {
            if(constants.getCode() .equals( code)){
                return constants;
            }
        }
        return PayChannelEnum.PAY_CHANNEL_CASH;
    }
}
