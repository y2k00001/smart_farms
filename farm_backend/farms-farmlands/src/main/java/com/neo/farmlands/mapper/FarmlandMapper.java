package com.neo.farmlands.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neo.farmlands.domain.Farmland;

/**
 * 农田信息Mapper接口
 *
 * @author neo
 * @date 2024-04-10
 */
public interface FarmlandMapper   extends BaseMapper<Farmland>
{
    /**
     * 查询农田信息
     *
     * @param id 农田信息主键
     * @return 农田信息
     */
    public Farmland selectFarmlandById(String id);

    /**
     * 查询农田信息列表
     *
     * @param farmland 农田信息
     * @return 农田信息集合
     */
    public List<Farmland> selectFarmlandList(Farmland farmland);

    /**
     * 新增农田信息
     *
     * @param farmland 农田信息
     * @return 结果
     */
    public int insertFarmland(Farmland farmland);

    /**
     * 修改农田信息
     *
     * @param farmland 农田信息
     * @return 结果
     */
    public int updateFarmland(Farmland farmland);

    /**
     * 删除农田信息
     *
     * @param id 农田信息主键
     * @return 结果
     */
    public int deleteFarmlandById(String id);

    /**
     * 批量删除农田信息
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFarmlandByIds(String[] ids);
}
