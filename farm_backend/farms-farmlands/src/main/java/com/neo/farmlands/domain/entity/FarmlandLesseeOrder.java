package com.neo.farmlands.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 租赁信息和订单关联表
 * @TableName t_farmland_lessee_order
 */
@TableName(value ="t_farmland_lessee_order")
@Data
public class FarmlandLesseeOrder implements Serializable {
    /**
     * id
     */
    @TableId
    private String id;

    /**
     * 农田租赁信息ID
     */
    private String farmlandLesseeId;

    /**
     * 账单ID
     */
    private String orderId;

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
