package com.neo.farmlands.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 账单支付单关联表
 * @TableName t_order_pay
 */
@TableName(value ="t_order_pay")
@Data
public class OrderPay implements Serializable {
    /**
     * id
     */
    @TableId
    private String id;

    /**
     * 账单ID
     */
    private String orderId;

    /**
     * 账单类型;1.租赁合同；2购买种子
     */
    private String orderType;

    /**
     * 支付单ID
     */
    private String payId;

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
    private Integer isDeleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
