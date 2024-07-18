package com.neo.farmlands.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neo.common.utils.DateUtils;
import com.neo.farmlands.domain.entity.FarmlandSeed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.neo.farmlands.mapper.FarmlandSeedMapper;

import com.neo.farmlands.service.IFarmlandSeedService;

/**
 * 农田、种子关联Service业务层处理
 *
 * @author neo
 * @date 2024-07-12
 */
@Service
public class FarmlandSeedServiceImpl extends ServiceImpl<FarmlandSeedMapper, FarmlandSeed> implements IFarmlandSeedService
{
    @Autowired
    private FarmlandSeedMapper farmlandSeedMapper;

    /**
     * 查询农田、种子关联
     *
     * @param id 农田、种子关联主键
     * @return 农田、种子关联
     */
    @Override
    public FarmlandSeed selectFarmlandSeedById(String id)
    {
        return farmlandSeedMapper.selectFarmlandSeedById(id);
    }

    /**
     * 查询农田、种子关联列表
     *
     * @param farmlandSeed 农田、种子关联
     * @return 农田、种子关联
     */
    @Override
    public List<FarmlandSeed> selectFarmlandSeedList(FarmlandSeed farmlandSeed)
    {
        return farmlandSeedMapper.selectFarmlandSeedList(farmlandSeed);
    }

    /**
     * 新增农田、种子关联
     *
     * @param farmlandSeed 农田、种子关联
     * @return 结果
     */
    @Override
    public int insertFarmlandSeed(FarmlandSeed farmlandSeed)
    {
        farmlandSeed.setCreateTime(DateUtils.getNowDate());
        return farmlandSeedMapper.insertFarmlandSeed(farmlandSeed);
    }

    /**
     * 修改农田、种子关联
     *
     * @param farmlandSeed 农田、种子关联
     * @return 结果
     */
    @Override
    public int updateFarmlandSeed(FarmlandSeed farmlandSeed)
    {
        farmlandSeed.setUpdateTime(DateUtils.getNowDate());
        return farmlandSeedMapper.updateFarmlandSeed(farmlandSeed);
    }

    /**
     * 批量删除农田、种子关联
     *
     * @param ids 需要删除的农田、种子关联主键
     * @return 结果
     */
    @Override
    public int deleteFarmlandSeedByIds(String[] ids)
    {
        return farmlandSeedMapper.deleteFarmlandSeedByIds(ids);
    }

    /**
     * 删除农田、种子关联信息
     *
     * @param id 农田、种子关联主键
     * @return 结果
     */
    @Override
    public int deleteFarmlandSeedById(String id)
    {
        return farmlandSeedMapper.deleteFarmlandSeedById(id);
    }
}
