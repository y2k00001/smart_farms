package com.neo.farmlands.domain.vo.form;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class OrderSubmitForm {
    @NotNull
    private Long addressId;
    private String note;
    /** 支付方式 0：未支付 1：支付宝 2：微信  默认微信 */
    private Integer payType = 2;
    /** 订单来源，购物车则为cart */
    private String from;

    /** 农田id */
    @ApiModelProperty(value = "农田id")
    private String farmlandId;


    /** 农场提供的服务id */
    @ApiModelProperty("农场提供的服务id")
    private String serviceId;

    /** 农田分割的地块面积id */
    @ApiModelProperty("农田分割的地块面积id")
    private String landAreaId;

    /** 租赁周期，单位：天 */
    @ApiModelProperty("租赁周期，单位：天")
    private Long lesseeDay;
    /** 种植的种子ID集合 */
    @ApiModelProperty("种植的种子ID集合")
    private List<String> seedIds;

    /** 租赁总价格 */
    @ApiModelProperty("租赁总价格")
    private String orderAmount;

    /** 租户姓名 */
    @ApiModelProperty("租户姓名")
    private String lesseeName;

    /** 租户电话 */
    @ApiModelProperty("租户电话")
    private String lesseePhone;

    /** 租赁备注 */
    @ApiModelProperty("租赁备注")
    private String lesseeRemark;



}
