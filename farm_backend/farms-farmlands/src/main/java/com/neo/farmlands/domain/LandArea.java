package com.neo.farmlands.domain;

import java.math.BigDecimal;
import com.neo.common.annotation.Excel;
import com.neo.common.core.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 农田租赁最小面积对象 t_land_area
 *
 * @author neo
 * @date 2024-04-19
 */
@Data
public class LandArea extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String id;

    /** 农田租赁面积编号Id */
    @Excel(name = "农田租赁面积编号Id")
    private String landAreaId;

    /** 面积别名 */
    @Excel(name = "面积别名")
    private String landAreaName;

    /** 面积;单位：平方米 */
    @Excel(name = "面积;单位：平方米")
    private BigDecimal area;

    /** 单价;单位：元/天 */
    @Excel(name = "单价;单位：元/天")
    private BigDecimal unitPrice;

    /** 排序序号 */
    @Excel(name = "排序序号")
    private Long sort;

    /** 创建人姓名 */
    @Excel(name = "创建人姓名")
    private String createByName;

    /** 是否删除 */
    @Excel(name = "是否删除")
    private String isDeleted;

}
