package com.neo.farmlands.domain.vo.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.neo.common.annotation.Excel;
import com.neo.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 田间管理记录对象 t_field_management_record
 *
 * @author neo
 * @date 2024-08-03
 */
@Data
@ApiModel("田间管理记录表单")
public class FieldManagementRecordForm extends BaseEntity
{
    private static final long serialVersionUID = 1L;


    /** 生长周期ID */
    @ApiModelProperty("成长周期ID")
    private String growthId;

    /** 操作类型;00 播种前准备，10，播种 20浇水；30，施肥，40除草，50 除虫，60防病，70，收获 */
    @ApiModelProperty("操作类型;00 播种前准备，10，播种 20浇水；30，施肥，40除草，50 除虫，60防病，70，收获")
    private Long operationType;

    /** 操作时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("操作时间")
    private Date operationTime;

    /** 操作状态 */
    @ApiModelProperty("操作状态")
    private Long operationStatus;

    /** 操作结果 */
    @ApiModelProperty("操作结果")
    private Long operationResult;

    /** 操作方式;10，线上代理；20线下预约 */
    @ApiModelProperty("操作方式;10，线上代理；20线下预约")
    private Long operationMode;

    @ApiModelProperty("附件id集合")
    private List<Long> fileIds;




}
