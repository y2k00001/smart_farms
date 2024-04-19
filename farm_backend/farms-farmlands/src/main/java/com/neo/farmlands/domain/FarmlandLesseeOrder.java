package com.neo.farmlands.domain;

import com.neo.common.annotation.Excel;
import com.neo.common.core.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 租赁信息和订单关联对象 t_farmland_lessee_order
 *
 * @author neo
 * @date 2024-04-19
 */
@Data
public class FarmlandLesseeOrder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private String id;

    /** 农田租赁信息ID */
    @Excel(name = "农田租赁信息ID")
    private String farmlandLesseeId;

    /** 账单ID */
    @Excel(name = "账单ID")
    private String orderId;

    /** 创建人姓名 */
    @Excel(name = "创建人姓名")
    private String createByName;

    /** 是否删除 */
    @Excel(name = "是否删除")
    private String isDeleted;

}
