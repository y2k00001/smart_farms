package com.neo.farmlands.domain.vo.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.neo.common.annotation.Excel;
import com.neo.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 资讯对象 t_news
 *
 * @author neo
 * @date 2024-06-13
 */
@Data
@ApiModel(value = "咨讯对象 t_news")
public class NewsForm extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "资讯ID")
    private String newsId;

    /** 咨讯类型 */
    @ApiModelProperty( "咨讯类型")
    private Long newsType;

    /** 标题; */
    @ApiModelProperty(value = "标题;")
    private String newTitle;

    /** 排序 */
    @ApiModelProperty(value = "排序")
    private Long sort;

    /** 封面缩略图ID*/
    @ApiModelProperty(value = "封面缩略图ID")
    private String thumbnailFile;

    /** 图片ID集合 */
    @ApiModelProperty(value = "图片ID集合")
    private String pictureIds;

    /** 内容 */
    @ApiModelProperty(value = "内容")
    private String content;

    /** 发布人 */
    @ApiModelProperty(value = "发布人")
    private String releaseBy;

    /** 发布时间 */
    @ApiModelProperty(value = "发布时间")
    private Date releaseTime;

    /** 开始时间 */
    @ApiModelProperty(value = "开始时间")
    private Date startTime;

    /** 结束时间 */
    @ApiModelProperty(value = "结束时间")
    private Date endTime;

    /** 资讯状态;1，显示，0隐藏 */
    @ApiModelProperty(value = "资讯状态;1，显示，0隐藏")
    private Long status;

    @ApiModelProperty(value = "创建人姓名")
    private String createByName;

    @ApiModelProperty(value = "是否删除")
    private Integer isDeleted;

}
