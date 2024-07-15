package com.neo.farmlands.domain.vo.form;

import com.baomidou.mybatisplus.annotation.TableName;
import com.neo.common.annotation.Excel;
import com.neo.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 服务信息对象 t_land_service
 *
 * @author neo
 * @date 2024-04-19
 */
@Data

public class LandServiceForm extends BaseEntity
{
    private static final long serialVersionUID = 1L;


    @ApiModelProperty("农田编号ID")
    private String farmlandId;

    /** 服务编号 */
    @ApiModelProperty("服务编号")
    private String serviceId;

    /** 服务名称 */
    @ApiModelProperty("服务名称")
    private String serviceName;

    /** 价格 */
    @ApiModelProperty("价格")
    private BigDecimal servicePrice;

    /** 类型;10，包种植、包配送；20，包种植；30，只出租土地，不包含其他服务 */
    @ApiModelProperty("类型;10，包种植、包配送；20，包种植；30，只出租土地，不包含其他服务")
    private Long serviceType;

    /** 说明 */
    @ApiModelProperty("说明")
    private String summary;



}
