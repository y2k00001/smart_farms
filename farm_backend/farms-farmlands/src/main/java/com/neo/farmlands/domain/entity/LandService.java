package com.neo.farmlands.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.neo.common.annotation.Excel;
import com.neo.common.core.domain.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 服务信息对象 t_land_service
 *
 * @author neo
 * @date 2024-04-19
 */
@Data
@TableName("t_land_service")
public class LandService extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    /** 服务编号 */
    @Excel(name = "服务编号")
    private String serviceId;

    /** 服务名称 */
    @Excel(name = "服务名称")
    private String serviceName;

    /** 价格 */
    @Excel(name = "价格")
    private BigDecimal servicePrice;

    /** 类型;10，包种植、包配送；20，包种植；30，只出租土地，不包含其他服务 */
    @Excel(name = "类型;10，包种植、包配送；20，包种植；30，只出租土地，不包含其他服务")
    private Long serviceType;

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
