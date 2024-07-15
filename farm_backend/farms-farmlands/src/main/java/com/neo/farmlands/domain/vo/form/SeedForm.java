package com.neo.farmlands.domain.vo.form;

import com.baomidou.mybatisplus.annotation.TableField;
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

    /** 种子名称 */
    @Excel(name = "种子名称")
    @ApiModelProperty("种子名称")
    private String seedName;

    /** 种子类型 */
    @Excel(name = "种子类型")
    @ApiModelProperty("种子类型")
    private Long seedType;


    /** 种子产地 */
    @Excel(name = "种子产地")
    @ApiModelProperty("种子产地")
    private String origin;

    /** 生长周期;单位天 */
    @Excel(name = "生长周期;单位天")
    @ApiModelProperty("生长周期;单位天")
    private Long growthCycle;

    /** 产量;单位吨/亩 */
    @Excel(name = "产量;单位吨/亩")
    @ApiModelProperty("产量;单位吨/亩")
    private BigDecimal yield;

    /** 播种季节 */
    @Excel(name = "播种季节")
    @ApiModelProperty("播种季节")
    private String seedtime;

    /** 适宜气候描述 */
    @Excel(name = "适宜气候描述")
    @ApiModelProperty("适宜气候描述")
    private String climate;

    /** 种植技术描述 */
    @Excel(name = "种植技术描述")
    @ApiModelProperty("种植技术描述")
    private String plantingTechnique;

    /** 备注 */
    @Excel(name = "备注")
    @ApiModelProperty("备注")
    private String remarks;

    /** 单价;单位元/斤 */
    @Excel(name = "单价;单位元/斤")
    @ApiModelProperty("单价;单位元/斤")
    private BigDecimal unitPrice;

    /** 库存数量 */
    @Excel(name = "库存数量")
    @ApiModelProperty("库存数量")
    private String stockTotal;

    /** 商品状态;0待上架，10在售，20已下架 */
    @Excel(name = "商品状态;0待上架，10在售，20已下架")
    @ApiModelProperty("商品状态;0待上架，10在售，20已下架")
    private String status;

    /** 附件ID集合;逗号分割 */
    @Excel(name = "附件ID集合;逗号分割")
    @ApiModelProperty("附件ID集合;逗号分割")
    private String fileIds;


}
