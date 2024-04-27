package com.neo.farmlands.mapper;

import java.util.List;
import com.neo.farmlands.domain.entity.PlotFarmland;

/**
 * 农场农田关联Mapper接口
 *
 * @author neo
 * @date 2024-04-16
 */
public interface PlotFarmlandMapper
{
    /**
     * 查询农场农田关联
     *
     * @param id 农场农田关联主键
     * @return 农场农田关联
     */
    public PlotFarmland selectPlotFarmlandById(String id);

    /**
     * 查询农场农田关联列表
     *
     * @param plotFarmland 农场农田关联
     * @return 农场农田关联集合
     */
    public List<PlotFarmland> selectPlotFarmlandList(PlotFarmland plotFarmland);

    /**
     * 新增农场农田关联
     *
     * @param plotFarmland 农场农田关联
     * @return 结果
     */
    public int insertPlotFarmland(PlotFarmland plotFarmland);

    /**
     * 修改农场农田关联
     *
     * @param plotFarmland 农场农田关联
     * @return 结果
     */
    public int updatePlotFarmland(PlotFarmland plotFarmland);

    /**
     * 删除农场农田关联
     *
     * @param id 农场农田关联主键
     * @return 结果
     */
    public int deletePlotFarmlandById(String id);

    /**
     * 批量删除农场农田关联
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePlotFarmlandByIds(String[] ids);
}
