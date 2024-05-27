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
public enum FarmlandLesseeStatusEnum {
    FARMLAND_LESSEE_STATUS_NO_PAY(0L, "待支付"),
    FARMLAND_LESSEE_STATUS_PLANTING(1L, "已生效"),

    FARMLAND_LESSEE_STATUS_FINISHED(2L, "已结束"),
    FARMLAND_LESSEE_STATUS_BREAK(3L, "违约"),
            ;



    private final Long code;
    private final String description;

    FarmlandLesseeStatusEnum(Long code, String description) {
        this.code = code;
        this.description = description;
    }

    public Long getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static FarmlandLesseeStatusEnum getByValue(Integer code){
        for (FarmlandLesseeStatusEnum constants : values()
        ) {
            if(constants.getCode() .equals( code)){
                return constants;
            }
        }
        return FarmlandLesseeStatusEnum.FARMLAND_LESSEE_STATUS_NO_PAY;
    }
}
