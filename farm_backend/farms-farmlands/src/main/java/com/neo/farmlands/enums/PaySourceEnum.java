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
public enum PaySourceEnum {
    // 支付来源;10.小程序，20，APP，30，H5


    // 微信小程序支付
    PAY_SOURCE_WX_LITE(10, "JSAPI"),

    PAY_CHANNEL_ALIPAY(20, "xxx"),

            ;



    private final Integer code;
    private final String description;

    PaySourceEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static PaySourceEnum getByValue(Integer code){
        for (PaySourceEnum constants : values()
        ) {
            if(constants.getCode() .equals( code)){
                return constants;
            }
        }
        return PaySourceEnum.PAY_SOURCE_WX_LITE;
    }
}
