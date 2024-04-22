package com.neo.farmlands.enums;
/**
 * @author monkey
 * @date 2024/4/21 15:35
 */

/**
 * 支付单状态枚举
 * 支付状态;00,待支付；10，支付中；20，支付成功；30-支付失败, 40-已撤销, 50-已退款, 60-订单关闭
 *@Author neo
 *@date 2024/4/21 15:35
 */
public enum PayStateEnum {

    PAY_STATE_NOT_PAY(00, "待支付"),

    // 微信小程序支付
    PAY_STATE_PAYING(10, "支付中"),

    PAY_STATE_PAY_SUCCESS(20, "支付成功"),
    PAY_STATE_PAY_FAILED(30, "支付失败"),
    PAY_STATE_PAY_CANCEL(40, "已撤销"),
    PAY_STATE_PAY_REFUND(50, "已退款"),
    PAY_STATE_PAY_CLOSED(60, "订单关闭"),
            ;



    private final Integer code;
    private final String description;

    PayStateEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static PayStateEnum getByValue(Integer code){
        for (PayStateEnum constants : values()
        ) {
            if(constants.getCode() .equals( code)){
                return constants;
            }
        }
        return PayStateEnum.PAY_STATE_NOT_PAY;
    }
}
