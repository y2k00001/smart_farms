package com.neo.farmlands.service.impl;

import java.util.List;
import com.neo.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.neo.farmlands.mapper.PlotFarmlandMapper;
import com.neo.farmlands.domain.PlotFarmland;
import com.neo.farmlands.service.IPlotFarmlandService;

/**
 * 农场农田关联Service业务层处理
 *
 * @author neo
 * @date 2024-04-16
 */
@Service
public class PlotFarmlandServiceImpl implements IPlotFarmlandService
{
    @Autowired
    private PlotFarmlandMapper plotFarmlandMapper;

    /**
     * 查询农场农田关联
     *
     * @param id 农场农田关联主键
     * @return 农场农田关联
     */
    @Override
    public PlotFarmland selectPlotFarmlandById(String id)
    {
        return plotFarmlandMapper.selectPlotFarmlandById(id);
    }

    /**
     * 查询农场农田关联列表
     *
     * @param plotFarmland 农场农田关联
     * @return 农场农田关联
     */
    @Override
    public List<PlotFarmland> selectPlotFarmlandList(PlotFarmland plotFarmland)
    {
        return plotFarmlandMapper.selectPlotFarmlandList(plotFarmland);
    }

    /**
     * 新增农场农田关联
     *
     * @param plotFarmland 农场农田关联
     * @return 结果
     */
    @Override
    public int insertPlotFarmland(PlotFarmland plotFarmland)
    {
        plotFarmland.setCreateTime(DateUtils.getNowDate());
        return plotFarmlandMapper.insertPlotFarmland(plotFarmland);
    }

    /**
     * 修改农场农田关联
     *
     * @param plotFarmland 农场农田关联
     * @return 结果
     */
    @Override
    public int updatePlotFarmland(PlotFarmland plotFarmland)
    {
        plotFarmland.setUpdateTime(DateUtils.getNowDate());
        return plotFarmlandMapper.updatePlotFarmland(plotFarmland);
    }

    /**
     * 批量删除农场农田关联
     *
     * @param ids 需要删除的农场农田关联主键
     * @return 结果
     */
    @Override
    public int deletePlotFarmlandByIds(String[] ids)
    {
        return plotFarmlandMapper.deletePlotFarmlandByIds(ids);
    }

    /**
     * 删除农场农田关联信息
     *
     * @param id 农场农田关联主键
     * @return 结果
     */
    @Override
    public int deletePlotFarmlandById(String id)
    {
        return plotFarmlandMapper.deletePlotFarmlandById(id);
    }
}
