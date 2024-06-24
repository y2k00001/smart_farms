package com.neo.farmlands.enums;
/**
 * @author monkey
 * @date 2024/4/21 15:35
 */

/**
 * order 农田租赁状态枚举
 * 租赁状态;0，待支付，1，已生效，2已结束，3违约
 *@Author neo
 *@date 2024/4/21 15:35
 */
public enum LesseeStatusEnum {
    LESSEE_STATUS_NO(0, "未出租"),
    LESSEE_STATUS_YES(1, "已出租"),


            ;



    private final Integer code;
    private final String description;

    LesseeStatusEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static LesseeStatusEnum getByValue(Integer code){
        for (LesseeStatusEnum constants : values()
        ) {
            if(constants.getCode() .equals( code)){
                return constants;
            }
        }
        return LesseeStatusEnum.LESSEE_STATUS_NO;
    }
}
