package com.neo.farmlands.domain.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.TableName;
import com.neo.common.annotation.Excel;
import com.neo.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 农作物生长周期信息对象 t_growth
 *
 * @author neo
 * @date 2024-06-03
 */
@ApiModel("农作物生长周期信息对象 t_growth")
@TableName("t_growth")
@Data
public class Growth extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private String id;

    /** 生长周期ID */
    @Excel(name = "生长周期ID")
    private String growthId;

    /** 农田ID */
    @Excel(name = "农田ID")
    private String farmlandId;

    /** 农田租赁合同ID */
    @Excel(name = "农田租赁合同ID")
    private String farmlandLesseeId;

    /** 种子ID */
    @Excel(name = "种子ID")
    private String seedId;

    /** 租赁面积ID */
    @Excel(name = "租赁面积ID")
    private String landAreaId;

    /** 农田信息快照 */
    @Excel(name = "农田信息快照")
    private String farmlandSnap;

    /** 种子信息快照 */
    @Excel(name = "种子信息快照")
    private String seedSnap;

    /** 农田昵称 */
    @Excel(name = "农田昵称")
    private String farmlanName;

    /** 农作物名称 */
    @Excel(name = "农作物名称")
    private String cropName;

    /** 学名 */
    @Excel(name = "学名")
    private String scientificName;

    /** 生长阶段;（如：播种期、生长期、成熟期等） */
    @Excel(name = "生长阶段;", readConverterExp = "如=：播种期、生长期、成熟期等")
    private Integer growthStage;

    /** 生长时间;（以天为单位） */
    @Excel(name = "生长时间;", readConverterExp = "以=天为单位")
    private String growthTime;

    /** 生长阶段的起始日期 */
    @Excel(name = "生长阶段的起始日期")
    private String startDate;

    /** 生长阶段的结束日期 */
    @Excel(name = "生长阶段的结束日期")
    private String endDate;

    /** 生长条件;（如土壤湿度、光照需求、温度范围等） */
    @Excel(name = "生长条件;", readConverterExp = "如=土壤湿度、光照需求、温度范围等")
    private String growthConditions;

    /** 施肥要求;（肥料种类、施肥时间、施肥量等） */
    @Excel(name = "施肥要求;", readConverterExp = "肥=料种类、施肥时间、施肥量等")
    private String fertilizerRequirements;

    /** 灌溉需求;（灌溉方式、灌溉量、灌溉频率等） */
    @Excel(name = "灌溉需求;", readConverterExp = "灌=溉方式、灌溉量、灌溉频率等")
    private String irrigationNeeds;

    /** 病虫害防治措施;（如农药使用、生物防治等） */
    @Excel(name = "病虫害防治措施;", readConverterExp = "如=农药使用、生物防治等")
    private String pestControlMeasures;

    /** 气候适应性;（描述作物对气候的敏感性和适应性） */
    @Excel(name = "气候适应性;", readConverterExp = "描=述作物对气候的敏感性和适应性")
    private String climateSuitability;

    /** 土壤类型;（如砂土、壤土、粘土等） */
    @Excel(name = "土壤类型;", readConverterExp = "如=砂土、壤土、粘土等")
    private String soilType;

    /** 生长阶段详细信息;（每个阶段的具体特点、注意事项等） */
    @Excel(name = "生长阶段详细信息;", readConverterExp = "每=个阶段的具体特点、注意事项等")
    private String growthPhaseDetails;

    /** 预期产量;（单位面积或单棵作物的预期产量） */
    @Excel(name = "预期产量;", readConverterExp = "单=位面积或单棵作物的预期产量")
    private BigDecimal yieldExpectation;

    /** 品种描述;（包括特性、优势等） */
    @Excel(name = "品种描述;", readConverterExp = "包=括特性、优势等")
    private String varietyDescription;

    /**  */
    @Excel(name = "")
    private String remarks;

    /** 创建人姓名 */
    @Excel(name = "创建人姓名")
    private String createByName;

    /** 是否删除 */
    @Excel(name = "是否删除")
    private Integer isDeleted;

}
