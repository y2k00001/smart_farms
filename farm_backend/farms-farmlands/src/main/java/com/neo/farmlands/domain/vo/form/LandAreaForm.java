package com.neo.farmlands.domain.vo.form;

import com.baomidou.mybatisplus.annotation.TableName;
import com.neo.common.annotation.Excel;
import com.neo.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 农田租赁最小面积对象 t_land_area
 *
 * @author neo
 * @date 2024-04-19
 */
@Data

public class LandAreaForm extends BaseEntity
{
    private static final long serialVersionUID = 1L;


    private String id;

    @ApiModelProperty("农田编号Id")
    private String farmlandId;

    /** 农田租赁面积编号Id */
    @ApiModelProperty("农田租赁面积编号Id")
    private String landAreaId;

    /** 面积别名 */
    @ApiModelProperty("面积别名")
    private String landAreaName;

    /** 面积;单位：平方米 */
    @ApiModelProperty("面积;单位：平方米")
    private BigDecimal area;

    /** 单价;单位：元/天 */
    @ApiModelProperty("单价;单位：元/天")
    private BigDecimal unitPrice;

    @ApiModelProperty("0，未出租；1，已出租")
    private Integer lesseeStatus;

    /** 排序序号 */
    @ApiModelProperty("排序序号")
    private Long sort;


}
