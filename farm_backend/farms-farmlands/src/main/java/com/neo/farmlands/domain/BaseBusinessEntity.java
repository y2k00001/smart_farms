package com.neo.farmlands.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.neo.common.core.domain.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 业务Entity基类
 *
 * @author ruoyi
 */
@Data
public class BaseBusinessEntity extends BaseEntity implements Serializable
{


    /** 创建者 */
    private String createByName;

    /** 是否删除  0，有效；1，已删除 */
    private Integer isDeleted;


}
