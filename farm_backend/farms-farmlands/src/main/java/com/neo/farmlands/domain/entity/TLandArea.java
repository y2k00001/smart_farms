package com.neo.farmlands.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 农田租赁最小面积表
 * @TableName t_land_area
 */
@TableName(value ="t_land_area")
@Data
public class TLandArea implements Serializable {
    /**
     *
     */
    @TableId
    private String id;

    /**
     * 农田租赁面积编号Id
     */
    private String landAreaId;

    /**
     * 面积别名
     */
    private String landAreaName;

    /**
     * 面积;单位：平方米
     */
    private BigDecimal area;

    /**
     * 单价;单位：元/天
     */
    private BigDecimal unitPrice;

    /**
     * 排序序号
     */
    private Integer sort;

    /**
     * 描述
     */
    private String remark;

    /**
     * 创建人姓名
     */
    private String createByName;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否删除
     */
    private String isDeleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
