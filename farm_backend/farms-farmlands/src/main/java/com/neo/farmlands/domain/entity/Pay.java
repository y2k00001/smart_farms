package com.neo.farmlands.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 支付记录表
 * @TableName t_pay
 */
@TableName(value ="t_pay")
@Data
public class Pay implements Serializable {
    /**
     * id
     */
    @TableId
    private String id;

    /**
     * 支付记录ID
     */
    private String payId;

    /**
     * 第三方订单号
     */
    private String thirdTradeNo;

    /**
     * 支付记录标题
     */
    private String subject;

    /**
     * 支付商品描述信息
     */
    private String body;

    /**
     * 支付来源;10.小程序，20，APP，30，H5
     */
    private Integer paySource;

    /**
     * 支付用户ID
     */
    private String payUserId;

    /**
     * 支付渠道;10.微信；20支付宝；30现金
     */
    private Integer payChannel;

    /**
     * 支付金额
     */
    private BigDecimal payAmount;

    /**
     * 后台修改的金额
     */
    private BigDecimal updateAmount;

    /**
     * 支付状态;00,待支付；10，支付中；20，支付成功；30-支付失败, 40-已撤销, 50-已退款, 60-订单关闭
     */
    private Integer payStatus;

    /**
     * 支付时间
     */
    private Date payTime;

    /**
     * 支付单过期时间
     */
    private Date expiredTime;

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
