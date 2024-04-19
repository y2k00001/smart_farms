package com.neo.farmlands.domain;

import com.neo.common.annotation.Excel;
import com.neo.common.core.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 租户信息对象 t_lessee
 *
 * @author neo
 * @date 2024-04-19
 */
@Data
public class Lessee extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private String id;

    /** 系统用户ID */
    @Excel(name = "系统用户ID")
    private String userId;

    /** 租户ID */
    @Excel(name = "租户ID")
    private String lesseeId;

    /** 租户姓名 */
    @Excel(name = "租户姓名")
    private String lesseeName;

    /** 租户手机号 */
    @Excel(name = "租户手机号")
    private Long lesseePhone;

    /** 创建人姓名 */
    @Excel(name = "创建人姓名")
    private String createByName;

    /** 是否删除 */
    @Excel(name = "是否删除")
    private Integer isDeleted;

}
