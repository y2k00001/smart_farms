package com.neo.farmlands.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.neo.common.annotation.Excel;
import com.neo.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 农田、服务关联对象 t_farmland_service
 *
 * @author neo
 * @date 2024-04-19
 */
@Data
@TableName(value ="t_farmland_service")
public class FarmlandService extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    /** 农田编号ID */
    @Excel(name = "农田编号ID")
    private String farmlandId;

    /** 服务id */
    @Excel(name = "服务id")
    private String serviceId;

    /** 创建人姓名 */
    @Excel(name = "创建人姓名")
    private String createByName;

    /** 是否删除 */
    @Excel(name = "是否删除")
    private String isDeleted;

}
