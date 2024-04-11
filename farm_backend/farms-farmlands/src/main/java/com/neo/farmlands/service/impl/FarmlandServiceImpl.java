package com.neo.farmlands.service.impl;

import java.util.List;
import com.neo.common.utils.DateUtils;
import com.neo.common.utils.SecurityUtils;
import com.neo.common.utils.uuid.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.neo.farmlands.mapper.FarmlandMapper;
import com.neo.farmlands.domain.Farmland;
import com.neo.farmlands.service.IFarmlandService;

/**
 * 农田信息Service业务层处理
 *
 * @author neo
 * @date 2024-04-10
 */
@Service
public class FarmlandServiceImpl implements IFarmlandService
{
    @Autowired
    private FarmlandMapper farmlandMapper;

    /**
     * 查询农田信息
     *
     * @param id 农田信息主键
     * @return 农田信息
     */
    @Override
    public Farmland selectFarmlandById(String id)
    {
        return farmlandMapper.selectFarmlandById(id);
    }

    /**
     * 查询农田信息列表
     *
     * @param farmland 农田信息
     * @return 农田信息
     */
    @Override
    public List<Farmland> selectFarmlandList(Farmland farmland)
    {
        return farmlandMapper.selectFarmlandList(farmland);
    }

    /**
     * 新增农田信息
     *
     * @param farmland 农田信息
     * @return 结果
     */
    @Override
    public int insertFarmland(Farmland farmland)
    {
        farmland.setId(IdUtils.fastSimpleUUID());
        farmland.setCreateTime(DateUtils.getNowDate());
        farmland.setCreateBy(SecurityUtils.getUserId().toString());
        farmland.setCreateByName(SecurityUtils.getUsername());
        return farmlandMapper.insertFarmland(farmland);
    }

    /**
     * 修改农田信息
     *
     * @param farmland 农田信息
     * @return 结果
     */
    @Override
    public int updateFarmland(Farmland farmland)
    {
        farmland.setUpdateTime(DateUtils.getNowDate());
        return farmlandMapper.updateFarmland(farmland);
    }

    /**
     * 批量删除农田信息
     *
     * @param ids 需要删除的农田信息主键
     * @return 结果
     */
    @Override
    public int deleteFarmlandByIds(String[] ids)
    {
        return farmlandMapper.deleteFarmlandByIds(ids);
    }

    /**
     * 删除农田信息信息
     *
     * @param id 农田信息主键
     * @return 结果
     */
    @Override
    public int deleteFarmlandById(String id)
    {
        return farmlandMapper.deleteFarmlandById(id);
    }
}
