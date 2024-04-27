package com.neo.farmlands.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 地址对象
 *
 */
@ApiModel(description="地址对象")
@Data
@TableName("sys_address")
public class Address {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("ID")
    private Integer id;

    @ApiModelProperty("地区邮编")

    private Long code;

    @ApiModelProperty("父地区邮编")

    private Long parentCode;

    @ApiModelProperty("地区名")
    private String name;

    @ApiModelProperty("地区层级")
    private Integer level;

    @ApiModelProperty("CREATED_AT")
    private String createdAt;

    @ApiModelProperty("UPDATED_AT")
    private String updatedAt;

    @ApiModelProperty("DELETED_AT")
    private String deletedAt;

}
