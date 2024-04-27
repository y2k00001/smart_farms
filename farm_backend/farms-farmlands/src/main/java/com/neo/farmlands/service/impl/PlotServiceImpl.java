package com.neo.farmlands.service.impl;

import java.util.List;
import com.neo.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.neo.farmlands.mapper.PlotMapper;
import com.neo.farmlands.domain.entity.Plot;
import com.neo.farmlands.service.IPlotService;

/**
 * 农场地块Service业务层处理
 *
 * @author neo
 * @date 2024-04-16
 */
@Service
public class PlotServiceImpl implements IPlotService
{
    @Autowired
    private PlotMapper plotMapper;

    /**
     * 查询农场地块
     *
     * @param id 农场地块主键
     * @return 农场地块
     */
    @Override
    public Plot selectPlotById(String id)
    {
        return plotMapper.selectPlotById(id);
    }

    /**
     * 查询农场地块列表
     *
     * @param plot 农场地块
     * @return 农场地块
     */
    @Override
    public List<Plot> selectPlotList(Plot plot)
    {
        return plotMapper.selectPlotList(plot);
    }

    /**
     * 新增农场地块
     *
     * @param plot 农场地块
     * @return 结果
     */
    @Override
    public int insertPlot(Plot plot)
    {
        plot.setCreateTime(DateUtils.getNowDate());
        return plotMapper.insertPlot(plot);
    }

    /**
     * 修改农场地块
     *
     * @param plot 农场地块
     * @return 结果
     */
    @Override
    public int updatePlot(Plot plot)
    {
        plot.setUpdateTime(DateUtils.getNowDate());
        return plotMapper.updatePlot(plot);
    }

    /**
     * 批量删除农场地块
     *
     * @param ids 需要删除的农场地块主键
     * @return 结果
     */
    @Override
    public int deletePlotByIds(String[] ids)
    {
        return plotMapper.deletePlotByIds(ids);
    }

    /**
     * 删除农场地块信息
     *
     * @param id 农场地块主键
     * @return 结果
     */
    @Override
    public int deletePlotById(String id)
    {
        return plotMapper.deletePlotById(id);
    }
}
