package com.neo.farmlands.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.neo.farmlands.domain.entity.FarmlandSeed;

import java.util.List;

/**
 * 农田、种子关联Service接口
 *
 * @author neo
 * @date 2024-07-12
 */
public interface IFarmlandSeedService extends IService<FarmlandSeed>
{
    /**
     * 查询农田、种子关联
     *
     * @param id 农田、种子关联主键
     * @return 农田、种子关联
     */
    public FarmlandSeed selectFarmlandSeedById(String id);

    /**
     * 查询农田、种子关联列表
     *
     * @param farmlandSeed 农田、种子关联
     * @return 农田、种子关联集合
     */
    public List<FarmlandSeed> selectFarmlandSeedList(FarmlandSeed farmlandSeed);

    /**
     * 新增农田、种子关联
     *
     * @param farmlandSeed 农田、种子关联
     * @return 结果
     */
    public int insertFarmlandSeed(FarmlandSeed farmlandSeed);

    /**
     * 修改农田、种子关联
     *
     * @param farmlandSeed 农田、种子关联
     * @return 结果
     */
    public int updateFarmlandSeed(FarmlandSeed farmlandSeed);

    /**
     * 批量删除农田、种子关联
     *
     * @param ids 需要删除的农田、种子关联主键集合
     * @return 结果
     */
    public int deleteFarmlandSeedByIds(String[] ids);

    /**
     * 删除农田、种子关联信息
     *
     * @param id 农田、种子关联主键
     * @return 结果
     */
    public int deleteFarmlandSeedById(String id);
}
