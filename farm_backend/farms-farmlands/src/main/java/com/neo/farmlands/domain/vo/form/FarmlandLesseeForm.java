package com.neo.farmlands.domain.vo.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.neo.common.annotation.Excel;
import com.neo.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 农田租赁信息对象 t_farmland_lessee
 *
 * @author neo
 * @date 2024-04-15
 */
@Data

public class FarmlandLesseeForm
{
    private static final long serialVersionUID = 1L;

    /** id */
    private String id;

    /** 农田租赁合同ID */
    @Excel(name = "农田租赁合同ID")
    private String farmlandLesseeId;

    /** 农田id */
    @Excel(name = "农田id")
    private String farmlandId;

    /** 租户id */
    @Excel(name = "租户id")
    private String lesseeId;

    @ApiModelProperty("用户ID")
    private Long memberId;


    @ApiModelProperty("地块面积id")
    private String landAreaId;

    @ApiModelProperty("租赁服务id")
    private String serviceId;

    /** 租赁单价;单位：元/天 */
    @Excel(name = "租赁单价;单位：元/天")
    private BigDecimal lesseePrice;

    /** 租赁总金额;单位：元 */
    @Excel(name = "租赁总金额;单位：元")
    private BigDecimal lesseeAmount;

    /** 租赁天数;单位:天 */
    @Excel(name = "租赁天数;单位:天")
    private Long lesseeDay;

    /** 租赁开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "租赁开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date lesseeStartDate;

    /** 租赁结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "租赁结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date lesseeEndDate;

    /** 租赁状态;0，待支付，1，已生效，2已结束，3违约 */
    @Excel(name = "租赁状态;0，待支付，1，已生效，2已结束，3违约")
    private Long status;

    /** 合同附件id集合 */
    @Excel(name = "合同附件id集合")
    private String lesseeFiles;

    /** 创建人姓名 */
    @Excel(name = "创建人姓名")
    private String createByName;

}
