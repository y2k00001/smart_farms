package com.neo.farmlands.domain.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
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
 * @date 2024-06-11
 */
@Data
@TableName("t_field_management_record")
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

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return id;
    }
    public void setFieldManagementRecordId(String fieldManagementRecordId)
    {
        this.fieldManagementRecordId = fieldManagementRecordId;
    }

    public String getFieldManagementRecordId()
    {
        return fieldManagementRecordId;
    }
    public void setGrowthId(String growthId)
    {
        this.growthId = growthId;
    }

    public String getGrowthId()
    {
        return growthId;
    }
    public void setOperationType(Long operationType)
    {
        this.operationType = operationType;
    }

    public Long getOperationType()
    {
        return operationType;
    }
    public void setOperationTime(Date operationTime)
    {
        this.operationTime = operationTime;
    }

    public Date getOperationTime()
    {
        return operationTime;
    }
    public void setOperationStatus(Long operationStatus)
    {
        this.operationStatus = operationStatus;
    }

    public Long getOperationStatus()
    {
        return operationStatus;
    }
    public void setOperationResult(Long operationResult)
    {
        this.operationResult = operationResult;
    }

    public Long getOperationResult()
    {
        return operationResult;
    }
    public void setOperationMode(Long operationMode)
    {
        this.operationMode = operationMode;
    }

    public Long getOperationMode()
    {
        return operationMode;
    }
    public void setCreateByName(String createByName)
    {
        this.createByName = createByName;
    }

    public String getCreateByName()
    {
        return createByName;
    }
    public void setIsDeleted(Integer isDeleted)
    {
        this.isDeleted = isDeleted;
    }

    public Integer getIsDeleted()
    {
        return isDeleted;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("fieldManagementRecordId", getFieldManagementRecordId())
            .append("growthId", getGrowthId())
            .append("operationType", getOperationType())
            .append("operationTime", getOperationTime())
            .append("operationStatus", getOperationStatus())
            .append("operationResult", getOperationResult())
            .append("operationMode", getOperationMode())
            .append("createByName", getCreateByName())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("isDeleted", getIsDeleted())
            .toString();
    }
}
