package com.neo.farmlands.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.neo.common.annotation.Excel;
import com.neo.common.core.domain.BaseEntity;
import com.neo.farmlands.domain.entity.LandArea;
import com.neo.farmlands.domain.entity.LandService;
import com.neo.farmlands.domain.entity.StorageFiles;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 租赁订单信息对象
 * @author neo
 * @date 2024-04-15
 */
@Data
public class LesseeOrderVO extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private String id;

    /** 农田租赁合同ID */
    @Excel(name = "农田租赁合同ID")
    @ApiModelProperty(value = "农田租赁合同ID")
    private String farmlandLesseeId;

    /** 农田id */
    @ApiModelProperty(value = "农田id")
    private String farmlandId;

    @ApiModelProperty("农田名称")
    private String farmlandName;

    /** 位置 */
    @Excel(name = "位置")
    @ApiModelProperty(value = "位置")
    private String location;

    /** 附件ID集合;逗号分割 */
    @Excel(name = "附件ID集合;逗号分割")
    @ApiModelProperty(value = "附件ID集合;逗号分割")
    private String fileIds;
    /** 附件数据 */
    @ApiModelProperty("附件数据")
    private List<StorageFiles> files;

    /** 租赁价格;单位：元/天 */
    @Excel(name = "租赁价格;单位：元/天")
    @ApiModelProperty(value = "租赁价格;单位：元/天")
    private BigDecimal leasePrice;

    /** 描述 */
    @Excel(name = "描述")
    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty("农田信息")
    private FarmlandVO farmlandVO;


    /** 农场提供的服务 */
    @ApiModelProperty("农场提供的服务")
    private LandService landServices;

    /** 农田分割的地块面积 */
    @ApiModelProperty("农田分割的地块面积")
    private LandArea landAreas;



    /** 租户id */
    @Excel(name = "租户id")
    @ApiModelProperty(value = "租户id")
    private String lesseeId;

    /** 租赁单价;单位：元/天 */
    @Excel(name = "租赁单价;单位：元/天")
    @ApiModelProperty(value = "租赁单价;单位：元/天")
    private BigDecimal lesseePrice;

    /** 租赁总金额;单位：元 */
    @Excel(name = "租赁总金额;单位：元")
    @ApiModelProperty(value = "租赁总金额;单位：元")
    private BigDecimal lesseeAmount;

    /** 租赁天数;单位:天 */
    @Excel(name = "租赁天数;单位:天")
    @ApiModelProperty(value = "租赁天数;单位:天")
    private Long lesseeDay;

    /** 租赁开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "租赁开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    @ApiModelProperty(value = "租赁开始时间")
    private Date lesseeStartDate;

    /** 租赁结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "租赁结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    @ApiModelProperty(value = "租赁结束时间")
    private Date lesseeEndDate;

    /** 租赁状态;0，待支付，1，已生效，2已结束，3违约 */
    @Excel(name = "租赁状态;0，待支付，1，已生效，2已结束，3违约")
    @ApiModelProperty(value = "租赁状态;0，待支付，1，已生效，2已结束，3违约")
    private Long status;

    /** 合同附件id集合 */
    @Excel(name = "合同附件id集合")
    @ApiModelProperty(value = "合同附件id集合")
    private String lesseeFiles;

    /** 创建人姓名 */
    @Excel(name = "创建人姓名")
    @ApiModelProperty(value = "创建人姓名")
    private String createByName;

    /** 是否删除 */
    @Excel(name = "是否删除")
    @ApiModelProperty(value = "是否删除")
    private Long isDeleted;

    @ApiModelProperty(value = "订单id")
    private String orderId;

}
