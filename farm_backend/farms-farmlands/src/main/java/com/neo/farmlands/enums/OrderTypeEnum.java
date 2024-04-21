package com.neo.farmlands.enums;
/**
 * @author monkey
 * @date 2024/4/21 15:35
 */

/**
 * order 订单类型枚举
 * TODO
 *@Author neo
 *@date 2024/4/21 15:35
 */
public enum OrderTypeEnum {
    ORDER_TYPE_FARMLAND_LESSEE("10", "租赁合同订单"),

    ORDER_TYPE_SEED("20", "种子购买订单"),
    ORDER_TYPE_SERVICE("30", "服务购买订单"),
            ;



    private final String code;
    private final String description;

    OrderTypeEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static OrderTypeEnum getByValue(Integer code){
        for (OrderTypeEnum constants : values()
        ) {
            if(constants.getCode() .equals( code)){
                return constants;
            }
        }
        return OrderTypeEnum.ORDER_TYPE_FARMLAND_LESSEE;
    }
}
