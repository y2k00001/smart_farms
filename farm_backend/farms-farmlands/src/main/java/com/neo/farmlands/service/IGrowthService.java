package com.neo.farmlands.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.neo.farmlands.domain.entity.Growth;
import com.neo.farmlands.domain.vo.GrowthVO;
import com.neo.farmlands.domain.vo.form.GrowthForm;

import java.util.List;


/**
 * 农作物生长周期信息Service接口
 *
 * @author neo
 * @date 2024-06-03
 */
public interface IGrowthService extends IService<Growth>
{
    /**
     * 查询农作物生长周期信息
     *
     * @param id 农作物生长周期信息主键
     * @return 农作物生长周期信息
     */
    public Growth selectGrowthById(String id);

    /**
     * 查询农作物生长周期信息列表
     *
     * @param growth 农作物生长周期信息
     * @return 农作物生长周期信息集合
     */
    public List<Growth> selectGrowthList(Growth growth);

    /**
     * 新增农作物生长周期信息
     *
     * @param growth 农作物生长周期信息
     * @return 结果
     */
    public int insertGrowth(Growth growth);

    /**
     * 修改农作物生长周期信息
     *
     * @param growth 农作物生长周期信息
     * @return 结果
     */
    public int updateGrowth(Growth growth);

    /**
     * 批量删除农作物生长周期信息
     *
     * @param ids 需要删除的农作物生长周期信息主键集合
     * @return 结果
     */
    public int deleteGrowthByIds(String[] ids);

    /**
     * 删除农作物生长周期信息信息
     *
     * @param id 农作物生长周期信息主键
     * @return 结果
     */
    public int deleteGrowthById(String id);

    List<GrowthVO> myGrowthList(GrowthForm growthForm);

    GrowthVO myFarmlandLesseeDetail(GrowthForm growthForm);
}
