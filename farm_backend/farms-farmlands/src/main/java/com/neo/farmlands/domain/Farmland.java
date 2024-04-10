package com.neo.farmlands.domain;

import java.math.BigDecimal;
import com.neo.common.annotation.Excel;
import com.neo.common.core.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 农田信息对象 t_farmland
 *
 * @author neo
 * @date 2024-04-10
 */
@Data
public class Farmland extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private String id;

    /** farmland_id */
    @Excel(name = "farmland_id")
    private String farmlandId;

    /** 面积单位;平方米 */
    @Excel(name = "面积单位;平方米")
    private String area;

    /** 维度 */
    @Excel(name = "维度")
    private String lat;

    /** 经度 */
    @Excel(name = "经度")
    private String lon;

    /** 位置 */
    @Excel(name = "位置")
    private String location;

    /** 农田状态;0.未上架；1，待租赁，2，已租赁 */
    @Excel(name = "农田状态;0.未上架；1，待租赁，2，已租赁")
    private String status;

    /** 租赁价格;单位：元/天 */
    @Excel(name = "租赁价格;单位：元/天")
    private BigDecimal leasePrice;

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
