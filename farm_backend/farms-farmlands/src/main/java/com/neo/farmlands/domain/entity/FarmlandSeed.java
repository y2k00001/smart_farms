package com.neo.farmlands.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.neo.common.annotation.Excel;
import com.neo.common.core.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 农田、种子关联对象 t_farmland_seed
 *
 * @author neo
 * @date 2024-07-12
 */
@Data
@TableName("t_farmland_seed")
public class FarmlandSeed
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String id;

    /** 农田编号ID */
    @Excel(name = "农田编号ID")
    private String farmlandId;

    /** 种子id */
    @Excel(name = "种子id")
    private String seedId;

    /** 创建人姓名 */
    @Excel(name = "创建人姓名")
    private String createByName;

    /** 是否删除 */
    @Excel(name = "是否删除")
    private Integer isDeleted;



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



}
