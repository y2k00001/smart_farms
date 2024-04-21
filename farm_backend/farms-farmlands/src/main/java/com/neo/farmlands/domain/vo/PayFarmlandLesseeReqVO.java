package com.neo.farmlands.domain.vo;

import com.neo.farmlands.domain.BaseBusinessEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 租赁合同支付提交类
 *
 * @author neo
 * @date 2024-04-15
 */
@Data
@ApiModel("租赁合同支付提交类")
public class PayFarmlandLesseeReqVO extends BaseBusinessEntity
{

    /** 农田id */
    @NotBlank
    @ApiModelProperty("农田租赁合同id")
    private String farmlandLesseeId;

    /** 支付渠道 */
    @NotNull
    @ApiModelProperty("支付渠道;10.微信；20支付宝；30现金")
    private Integer payChannel;



    /** 支付来源 */
    @ApiModelProperty("支付来源")
    @NotBlank
    private Integer paySource;


    /** 支付来源参数 */
    @ApiModelProperty("支付来源参数")
    private String paySourceParam;


}
