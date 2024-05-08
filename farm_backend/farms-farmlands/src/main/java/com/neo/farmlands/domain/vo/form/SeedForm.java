package com.neo.farmlands.domain.vo.form;

import com.neo.common.annotation.Excel;
import com.neo.common.core.domain.BaseEntity;
import com.neo.farmlands.domain.entity.StorageFiles;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 种子信息对象 t_seed
 *
 * @author neo
 * @date 2024-04-16
 */
@Data
public class SeedForm
{
    private static final long serialVersionUID = 1L;


    /** 种子ID */
    @ApiModelProperty("种子ID")
    private String seedId;

    @ApiModelProperty("种子ID集合")
    private String[] seedIds;

    /** 种子名称 */
    @Excel(name = "种子名称")
    @ApiModelProperty("种子名称")
    private String seedName;

    /** 种子类型 */
    @Excel(name = "种子类型")
    @ApiModelProperty("种子类型")
    private Long seedType;






    /** 商品状态;0待上架，10在售，20已下架 */
    @Excel(name = "商品状态;0待上架，10在售，20已下架")
    private String status;





}
