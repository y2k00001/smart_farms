package com.neo.farmlands.domain.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.neo.common.annotation.Excel;
import com.neo.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 资讯对象 t_news
 *
 * @author neo
 * @date 2024-06-13
 */
@Data
public class News extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private String id;

    /** 资讯ID */
    @Excel(name = "资讯ID")
    private String newsId;

    /** 咨讯类型 */
    @Excel(name = "咨讯类型")
    private Long newsType;

    /** 标题 */
    @ApiModelProperty(value = "标题")
    private String newTitle;


    /** 摘要 */
    @ApiModelProperty(value = "摘要")
    private String summary;

    /** 排序 */
    @Excel(name = "排序")
    private Long sort;

    /** 封面缩略图ID;1，是，0，否 */
    @Excel(name = "封面缩略图ID;1，是，0，否")
    private String thumbnailFile;

    /** 图片ID集合 */
    @Excel(name = "图片ID集合")
    private String pictureIds;

    /** 内容 */
    @Excel(name = "内容")
    private String content;

    /** 发布人 */
    @Excel(name = "发布人")
    private String releaseBy;

    /** 发布时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "发布时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date releaseTime;

    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    /** 资讯状态;1，显示，0隐藏 */
    @Excel(name = "资讯状态;1，显示，0隐藏")
    private Long status;

    /** 创建人姓名 */
    @Excel(name = "创建人姓名")
    private String createByName;

    /** 是否删除 */
    @Excel(name = "是否删除")
    private Long isDeleted;

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return id;
    }
    public void setNewsId(String newsId)
    {
        this.newsId = newsId;
    }

    public String getNewsId()
    {
        return newsId;
    }
    public void setNewsType(Long newsType)
    {
        this.newsType = newsType;
    }

    public Long getNewsType()
    {
        return newsType;
    }
    public void setNewTitle(String newTitle)
    {
        this.newTitle = newTitle;
    }

    public String getNewTitle()
    {
        return newTitle;
    }
    public void setSort(Long sort)
    {
        this.sort = sort;
    }

    public Long getSort()
    {
        return sort;
    }
    public void setThumbnailFile(String thumbnailFile)
    {
        this.thumbnailFile = thumbnailFile;
    }

    public String getThumbnailFile()
    {
        return thumbnailFile;
    }
    public void setPictureIds(String pictureIds)
    {
        this.pictureIds = pictureIds;
    }

    public String getPictureIds()
    {
        return pictureIds;
    }
    public void setContent(String content)
    {
        this.content = content;
    }

    public String getContent()
    {
        return content;
    }
    public void setReleaseBy(String releaseBy)
    {
        this.releaseBy = releaseBy;
    }

    public String getReleaseBy()
    {
        return releaseBy;
    }
    public void setReleaseTime(Date releaseTime)
    {
        this.releaseTime = releaseTime;
    }

    public Date getReleaseTime()
    {
        return releaseTime;
    }
    public void setStartTime(Date startTime)
    {
        this.startTime = startTime;
    }

    public Date getStartTime()
    {
        return startTime;
    }
    public void setEndTime(Date endTime)
    {
        this.endTime = endTime;
    }

    public Date getEndTime()
    {
        return endTime;
    }
    public void setStatus(Long status)
    {
        this.status = status;
    }

    public Long getStatus()
    {
        return status;
    }
    public void setCreateByName(String createByName)
    {
        this.createByName = createByName;
    }

    public String getCreateByName()
    {
        return createByName;
    }
    public void setIsDeleted(Long isDeleted)
    {
        this.isDeleted = isDeleted;
    }

    public Long getIsDeleted()
    {
        return isDeleted;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("newsId", getNewsId())
            .append("newsType", getNewsType())
            .append("newTitle", getNewTitle())
            .append("sort", getSort())
            .append("thumbnailFile", getThumbnailFile())
            .append("pictureIds", getPictureIds())
            .append("content", getContent())
            .append("releaseBy", getReleaseBy())
            .append("releaseTime", getReleaseTime())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .append("status", getStatus())
            .append("createByName", getCreateByName())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("isDeleted", getIsDeleted())
            .toString();
    }
}
