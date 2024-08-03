package com.neo.farmlands.domain.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.neo.common.annotation.Excel;
import com.neo.common.core.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 田间管理记录对象 t_field_management_record
 *
 * @author neo
 * @date 2024-08-03
 */
@Data
public class FieldManagementRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private String id;

    /** 记录ID */
    @Excel(name = "记录ID")
    private String fieldManagementRecordId;

    /** 生长周期ID */
    @Excel(name = "生长周期ID")
    private String growthId;

    /** 操作类型;00 播种前准备，10，播种 20浇水；30，施肥，40除草，50 除虫，60防病，70，收获 */
    @Excel(name = "操作类型;00 播种前准备，10，播种 20浇水；30，施肥，40除草，50 除虫，60防病，70，收获")
    private Long operationType;

    /** 操作时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "操作时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date operationTime;

    /** 操作状态 */
    @Excel(name = "操作状态")
    private Long operationStatus;

    /** 操作结果 */
    @Excel(name = "操作结果")
    private Long operationResult;

    /** 操作方式;10，线上代理；20线下预约 */
    @Excel(name = "操作方式;10，线上代理；20线下预约")
    private Long operationMode;

    /** 创建人姓名 */
    @Excel(name = "创建人姓名")
    private String createByName;

    /** 是否删除 */
    @Excel(name = "是否删除")
    private Integer isDeleted;

}
