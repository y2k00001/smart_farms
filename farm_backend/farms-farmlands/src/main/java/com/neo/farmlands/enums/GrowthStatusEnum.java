package com.neo.farmlands.enums;
/**
 * @author monkey
 * @date 2024/4/21 15:35
 */

/**
 * order 作物生长周期状态枚举
 * 租赁状态;0，待支付，1，已生效，2已结束，3违约
 *@Author neo
 *@date 2024/4/21 15:35
 */
public enum GrowthStatusEnum {

    // 播种状态
    GROWTH_STATUS_SEED(10L, "播种期"),
    GROWTH_STATUS_GROWING(20L, "生长期"),

    GROWTH_STATUS_HARVEST(30L, "收获期"),
    GROWTH_STATUS_FINISHED(40L, "结束期"),

            ;



    private final Long code;
    private final String description;

    GrowthStatusEnum(Long code, String description) {
        this.code = code;
        this.description = description;
    }

    public Long getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static GrowthStatusEnum getByValue(Integer code){
        for (GrowthStatusEnum constants : values()
        ) {
            if(constants.getCode() .equals( code)){
                return constants;
            }
        }
        return GrowthStatusEnum.GROWTH_STATUS_SEED;
    }
}
