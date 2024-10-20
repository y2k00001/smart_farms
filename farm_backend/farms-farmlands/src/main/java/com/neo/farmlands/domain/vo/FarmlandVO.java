package com.neo.farmlands.domain.vo;

import com.neo.common.annotation.Excel;
import com.neo.common.core.domain.BaseEntity;
import com.neo.farmlands.domain.entity.LandArea;
import com.neo.farmlands.domain.entity.LandService;
import com.neo.farmlands.domain.entity.Seed;
import com.neo.farmlands.domain.entity.StorageFiles;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 农田信息对象 t_farmland
 *
 * @author neo
 * @date 2024-04-10
 */
@Data
public class FarmlandVO extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private String id;

    /** farmland_id */
    @Excel(name = "farmland_id")
    private String farmlandId;

    @ApiModelProperty("农田名称")
    private String farmlandName;


    /** 面积单位;平方米 */
    @Excel(name = "面积单位;平方米")
    private String area;

    /** 维度 */
    @Excel(name = "维度")
    private String lat;

    /** 经度 */
    @Excel(name = "经度")
    private String lon;

    /** 位置 */
    @Excel(name = "位置")
    private String location;

    /** 农田状态;0.未上架；1，待租赁，2，已租赁 */
    @Excel(name = "农田状态;0.未上架；1，待租赁，2，已租赁")
    private String status;

    /** 租赁价格;单位：元/天 */
    @Excel(name = "租赁价格;单位：元/天")
    private BigDecimal leasePrice;

    /** 摘要 */
    @ApiModelProperty("摘要")
    private String summary;

    /** 描述 */
    @Excel(name = "描述")
    private String description;

    /** 联系电话 */
    private String contactPhone;

    /** 附件ID集合;逗号分割 */
    @Excel(name = "附件ID集合;逗号分割")
    private String fileIds;


    /** 附件数据 */
    @ApiModelProperty("附件数据")
    private List<StorageFiles> files;

    /** 农场提供的服务 */
    @ApiModelProperty("农场提供的服务")
    private List<LandService> landServices;

    /** 农田分割的地块面积 */
    @ApiModelProperty("农田分割的地块面积")
    private List<LandArea> landAreas;

    /** 农田可以播种的种子 */
    @ApiModelProperty("农田可以播种的种子")
    private List<SeedVO> seedVOS;

    /** 创建人姓名 */
    @Excel(name = "创建人姓名")
    private String createByName;

    /** 是否删除 */
    @Excel(name = "是否删除")
    private String isDeleted;

}
