package com.neo.farmlands.domain.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.neo.common.annotation.Excel;
import com.neo.common.core.domain.BaseEntity;
import com.neo.farmlands.domain.entity.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 农田租赁信息对象 t_farmland_lessee
 *
 * @author neo
 * @date 2024-04-15
 */
@Data

public class FarmlandLesseeVO
{
    private static final long serialVersionUID = 1L;

    /** id */
    private String id;

    /** 农田租赁合同ID */
   @ApiModelProperty(name = "农田租赁合同ID")
    private String farmlandLesseeId;

    /** 农田id */
   @ApiModelProperty(name = "农田id")
    private String farmlandId;

    /** 农田详细信息 */
    private FarmlandVO farmlandVO;

    /** 租户id */
   @ApiModelProperty(name = "租户id")
    private String lesseeId;

    @ApiModelProperty("用户ID")
    private Long memberId;


    @ApiModelProperty("地块面积id")
    private String landAreaId;

    @ApiModelProperty("地块面积信息")
    private LandArea landArea;

    @ApiModelProperty("租赁服务id")
    private String serviceId;

    @ApiModelProperty("租赁服务信息")
    private LandService landService;

    /** 租赁单价;单位：元/天 */
   @ApiModelProperty(name = "租赁单价;单位：元/天")
    private BigDecimal lesseePrice;

    /** 租赁总金额;单位：元 */
   @ApiModelProperty(name = "租赁总金额;单位：元")
    private BigDecimal lesseeAmount;

    /** 租赁天数;单位:天 */
   @ApiModelProperty(name = "租赁天数;单位:天")
    private Long lesseeDay;

   @ApiModelProperty("租赁种植种子列表")
    private List<Seed> seedList;

   @ApiModelProperty("租户信息")
   private Lessee lessee;

    @ApiModelProperty("租赁土地的关联订单信息")
    private FarmlandLesseeOrder farmlandLesseeOrder;

    /** 租赁开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
   @ApiModelProperty(name = "租赁开始时间")
    private Date lesseeStartDate;

    /** 租赁结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
   @ApiModelProperty(name = "租赁结束时间")
    private Date lesseeEndDate;

    /** 租赁状态;0，待支付，1，已生效，2已结束，3违约 */
   @ApiModelProperty(name = "租赁状态;0，待支付，1，已生效，2已结束，3违约")
    private Long status;

    /** 合同附件id集合 */
   @ApiModelProperty(name = "合同附件id集合")
    private String lesseeFiles;

    /** 创建人姓名 */
   @ApiModelProperty(name = "创建人姓名")
    private String createByName;

}
