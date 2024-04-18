package com.neo.farmlands.service;

import java.util.List;
import com.neo.farmlands.domain.Plot;

/**
 * 农场地块Service接口
 * 
 * @author neo
 * @date 2024-04-16
 */
public interface IPlotService 
{
    /**
     * 查询农场地块
     * 
     * @param id 农场地块主键
     * @return 农场地块
     */
    public Plot selectPlotById(String id);

    /**
     * 查询农场地块列表
     * 
     * @param plot 农场地块
     * @return 农场地块集合
     */
    public List<Plot> selectPlotList(Plot plot);

    /**
     * 新增农场地块
     * 
     * @param plot 农场地块
     * @return 结果
     */
    public int insertPlot(Plot plot);

    /**
     * 修改农场地块
     * 
     * @param plot 农场地块
     * @return 结果
     */
    public int updatePlot(Plot plot);

    /**
     * 批量删除农场地块
     * 
     * @param ids 需要删除的农场地块主键集合
     * @return 结果
     */
    public int deletePlotByIds(String[] ids);

    /**
     * 删除农场地块信息
     * 
     * @param id 农场地块主键
     * @return 结果
     */
    public int deletePlotById(String id);
}
