package com.neo.farmlands.domain;

import com.neo.common.annotation.Excel;
import com.neo.common.core.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 农场地块对象 t_plot
 *
 * @author neo
 * @date 2024-04-16
 */
@Data
public class Plot extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String id;

    /** 农场编号 */
    @Excel(name = "农场编号")
    private String plotId;

    /** 农场地块名称 */
    @Excel(name = "农场地块名称")
    private String plotName;

    /** 面积单位;平方米 */
    @Excel(name = "面积单位;平方米")
    private String area;

    /** 维度 */
    @Excel(name = "维度")
    private String lat;

    /** 经度 */
    @Excel(name = "经度")
    private String lon;

    /** 负责人姓名 */
    @Excel(name = "负责人姓名")
    private String ownerName;

    /** 负责人手机号 */
    @Excel(name = "负责人手机号")
    private Long ownerPhone;

    /** 位置 */
    @Excel(name = "位置")
    private String location;

    /** 农场地块状态;0.未上架；1，待租赁，2，已租赁 */
    @Excel(name = "农场地块状态;0.未上架；1，待租赁，2，已租赁")
    private String status;

    /** 描述 */
    @Excel(name = "描述")
    private String description;

    /** 附件ID集合;逗号分割 */
    @Excel(name = "附件ID集合;逗号分割")
    private String fileIds;

    /** 创建人姓名 */
    @Excel(name = "创建人姓名")
    private String createByName;

    /** 是否删除 */
    @Excel(name = "是否删除")
    private String isDeleted;

}
