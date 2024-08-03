package com.neo.farmlands.domain.entity;

import com.neo.common.annotation.Excel;
import com.neo.common.core.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 田间管理记录附件对象 t_field_management_record_files
 *
 * @author neo
 * @date 2024-08-03
 */
@Data
public class FieldManagementRecordFiles extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private String id;

    /** 记录ID */
    @Excel(name = "记录ID")
    private String fieldManagementRecordId;

    /** 附件id */
    @Excel(name = "附件id")
    private String fileId;

    /** 创建人姓名 */
    @Excel(name = "创建人姓名")
    private String createByName;

    /** 是否删除 */
    @Excel(name = "是否删除")
    private Integer isDeleted;

}
