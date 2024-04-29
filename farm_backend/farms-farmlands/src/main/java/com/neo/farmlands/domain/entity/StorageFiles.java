package com.neo.farmlands.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 存储资源文件
 * @TableName t_storage_files
 */
@TableName(value ="t_storage_files")
@Data
public class StorageFiles implements Serializable {
    /**
     * id
     */
    @TableId
    private String id;

    /**
     * 文件ID
     */
    private String fileId;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 原始文件名
     */
    private String originalName;

    /**
     * 文件后缀
     */
    private String fileSuffix;

    /**
     * url 地址
     */
    private String url;

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
