package com.neo.farmlands.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.neo.common.annotation.Excel;
import com.neo.common.core.domain.BaseEntity;
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
@TableName("t_seed")
public class Seed extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private String id;

    /** 种子ID */
    @Excel(name = "种子ID")
    private String seedId;

    /** 种子名称 */
    @Excel(name = "种子名称")
    private String seedName;

    /** 种子类型 */
    @Excel(name = "种子类型")
    private Long seedType;

    /** 种子产地 */
    @Excel(name = "种子产地")
    private String origin;

    /** 生长周期;单位天 */
    @Excel(name = "生长周期;单位天")
    private Long growthCycle;

    /** 产量;单位吨/亩 */
    @Excel(name = "产量;单位吨/亩")
    private BigDecimal yield;

    /** 播种季节 */
    @Excel(name = "播种季节")
    private String seedtime;

    /** 适宜气候描述 */
    @Excel(name = "适宜气候描述")
    private String climate;

    /** 种植技术描述 */
    @Excel(name = "种植技术描述")
    private String plantingTechnique;

    /** 备注 */
    @Excel(name = "备注")
    private String remarks;

    /** 单价;单位元/斤 */
    @Excel(name = "单价;单位元/斤")
    private BigDecimal unitPrice;

    /** 库存数量 */
    @Excel(name = "库存数量")
    private String stockTotal;

    /** 商品状态;0待上架，10在售，20已下架 */
    @Excel(name = "商品状态;0待上架，10在售，20已下架")
    private String status;

    /** 附件ID集合;逗号分割 */
    @Excel(name = "附件ID集合;逗号分割")
    private String fileIds;



    /** 创建人姓名 */
    @Excel(name = "创建人姓名")
    private String createByName;

    /** 是否删除 */
    @Excel(name = "是否删除")
    private Long isDeleted;

    @TableField(exist = false)
    private List<StorageFiles> files;

}
