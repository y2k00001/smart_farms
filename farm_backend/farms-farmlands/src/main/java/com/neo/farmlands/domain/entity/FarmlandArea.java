package com.neo.farmlands.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 农田、租赁面积关联表
 * @TableName t_farmland_area
 */
@TableName(value ="t_farmland_area")
@Data
public class FarmlandArea implements Serializable {
    /**
     *
     */
    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 农田编号ID
     */
    private String farmlandId;

    /**
     * 农田租赁面积id
     */
    private String landAreaId;

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
