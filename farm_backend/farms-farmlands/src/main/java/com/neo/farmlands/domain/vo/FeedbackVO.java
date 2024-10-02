package com.neo.farmlands.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.neo.common.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
/**
 * 意见反馈 数据视图对象
 *
 * @author zcc
 */
@Data
public class FeedbackVO  {
   /** ID */
    private Long id;
   /** 类型 */
    @ApiModelProperty(value = "类型")
    private String type;
   /** 具体说明 */
   @ApiModelProperty(value = "具体说明")
    private String content;
   /** 图片 */
    @ApiModelProperty(value = "图片")
    private String images;
   /** 联系电话 */
    @ApiModelProperty(value = "联系电话")
    private String phone;
   /** 创建人 */
    @ApiModelProperty(value = "创建人")
   /** 创建时间 */
    private LocalDateTime createTime;
   /** 处理状态 0：未处理  1：已处理 */
    @ApiModelProperty(value = "处理状态 0：未处理  1：已处理")
    private Integer handleStatus;
   /** 备注 */
    @ApiModelProperty(value = "备注")
    private String remark;
   /** 处理时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "处理时间")
    private LocalDateTime handleTime;
}
