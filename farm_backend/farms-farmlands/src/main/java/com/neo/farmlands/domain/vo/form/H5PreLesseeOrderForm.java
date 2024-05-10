package com.neo.farmlands.domain.vo.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.neo.common.annotation.Excel;
import com.neo.common.core.domain.BaseEntity;
import com.neo.farmlands.domain.entity.LandArea;
import com.neo.farmlands.domain.entity.LandService;
import com.neo.farmlands.domain.vo.FarmlandVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 预下单租赁订单请求对象
 * @author neo
 * @date 2024-04-15
 */
@Data
public class H5PreLesseeOrderForm extends BaseEntity
{
    private static final long serialVersionUID = 1L;


    /** 农田id */
    @ApiModelProperty(value = "农田id")
    private String farmlandId;


    /** 农场提供的服务id */
    @ApiModelProperty("农场提供的服务id")
    private String serviceId;

    /** 农田分割的地块面积id */
    @ApiModelProperty("农田分割的地块面积id")
    private String landAreaId;


}
