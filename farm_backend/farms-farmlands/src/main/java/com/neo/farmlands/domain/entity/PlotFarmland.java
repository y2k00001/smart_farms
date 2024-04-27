package com.neo.farmlands.domain.entity;

import com.neo.common.annotation.Excel;
import com.neo.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 农场农田关联对象 t_plot_farmland
 *
 * @author neo
 * @date 2024-04-16
 */
@Data
public class PlotFarmland extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String id;

    /** 农场编号 */
    @Excel(name = "农场编号")
    private String plotId;

    /** 农田编号 */
    @Excel(name = "农田编号")
    private String farmlandId;

    /** 创建人姓名 */
    @Excel(name = "创建人姓名")
    private String createByName;

    /** 是否删除 */
    @Excel(name = "是否删除")
    private Integer isDeleted;

}
