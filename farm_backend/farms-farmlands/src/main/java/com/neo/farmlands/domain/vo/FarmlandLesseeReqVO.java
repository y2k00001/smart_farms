package com.neo.farmlands.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.neo.common.annotation.Excel;
import com.neo.common.core.domain.BaseEntity;
import com.neo.farmlands.domain.BaseBusinessEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 农田租赁提交类
 *
 * @author neo
 * @date 2024-04-15
 */
@Data
@ApiModel("农田租赁提交类")
public class FarmlandLesseeReqVO extends BaseBusinessEntity
{

    /** 农田id */
    @NotBlank
    @ApiModelProperty("农田id")
    private String farmlandId;

    /** 农田租赁面积编号Id */
    @NotBlank
    @ApiModelProperty("农田租赁面积编号Id")
    private String landAreaId;


    /** 种子ID */
    @ApiModelProperty("种子ID")
    @NotBlank
    private String seedId;

    /** 服务编号 */
    @ApiModelProperty("服务编号")
    @NotBlank
    private String serviceId;

    /** 租赁天数;单位:天 */
    @ApiModelProperty( "租赁天数;单位:天")
    private Long lesseeDay;

    /** 租户姓名 */
    @NotBlank
    @ApiModelProperty("租户姓名")
    private String lesseeName;

    /** 租户手机号 */
    @ApiModelProperty("租户手机号")
    @NotBlank
    private Long lesseePhone;

    /** 租户备注 */
    @ApiModelProperty("租户备注")
    private String lesseeRemark;

}
