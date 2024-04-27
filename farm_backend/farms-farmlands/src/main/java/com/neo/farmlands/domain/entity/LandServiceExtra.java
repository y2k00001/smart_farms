package com.neo.farmlands.domain.entity;

import com.neo.common.annotation.Excel;
import com.neo.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 服务信息扩展对象 t_land_service_extra
 *
 * @author neo
 * @date 2024-04-19
 */
@Data
public class LandServiceExtra extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String id;

    /** 服务编号 */
    @Excel(name = "服务编号")
    private String serviceId;

    /** 服务扩展编号 */
    @Excel(name = "服务扩展编号")
    private String extraId;

    /** 特色服务名称 */
    @Excel(name = "特色服务名称")
    private String extraName;

    /** 附件;10，包种植、包配送；20，包种植；30，只出租土地，不包含其他服务 */
    @Excel(name = "附件;10，包种植、包配送；20，包种植；30，只出租土地，不包含其他服务")
    private String extraFiles;

    /** 说明 */
    @Excel(name = "说明")
    private String summary;

    /** 创建人姓名 */
    @Excel(name = "创建人姓名")
    private String createByName;

    /** 是否删除 */
    @Excel(name = "是否删除")
    private String isDeleted;

}
