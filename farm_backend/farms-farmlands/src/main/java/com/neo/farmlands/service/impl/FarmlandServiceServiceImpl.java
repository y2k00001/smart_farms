package com.neo.farmlands.service.impl;

import java.util.List;
import com.neo.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.neo.farmlands.mapper.FarmlandServiceMapper;
import com.neo.farmlands.domain.entity.FarmlandService;
import com.neo.farmlands.service.IFarmlandServiceService;

/**
 * 农田、服务关联Service业务层处理
 *
 * @author neo
 * @date 2024-04-19
 */
@Service
public class FarmlandServiceServiceImpl implements IFarmlandServiceService
{
    @Autowired
    private FarmlandServiceMapper farmlandServiceMapper;

    /**
     * 查询农田、服务关联
     *
     * @param id 农田、服务关联主键
     * @return 农田、服务关联
     */
    @Override
    public FarmlandService selectFarmlandServiceById(String id)
    {
        return farmlandServiceMapper.selectFarmlandServiceById(id);
    }

    /**
     * 查询农田、服务关联列表
     *
     * @param farmlandService 农田、服务关联
     * @return 农田、服务关联
     */
    @Override
    public List<FarmlandService> selectFarmlandServiceList(FarmlandService farmlandService)
    {
        return farmlandServiceMapper.selectFarmlandServiceList(farmlandService);
    }

    /**
     * 新增农田、服务关联
     *
     * @param farmlandService 农田、服务关联
     * @return 结果
     */
    @Override
    public int insertFarmlandService(FarmlandService farmlandService)
    {
        farmlandService.setCreateTime(DateUtils.getNowDate());
        return farmlandServiceMapper.insertFarmlandService(farmlandService);
    }

    /**
     * 修改农田、服务关联
     *
     * @param farmlandService 农田、服务关联
     * @return 结果
     */
    @Override
    public int updateFarmlandService(FarmlandService farmlandService)
    {
        farmlandService.setUpdateTime(DateUtils.getNowDate());
        return farmlandServiceMapper.updateFarmlandService(farmlandService);
    }

    /**
     * 批量删除农田、服务关联
     *
     * @param ids 需要删除的农田、服务关联主键
     * @return 结果
     */
    @Override
    public int deleteFarmlandServiceByIds(String[] ids)
    {
        return farmlandServiceMapper.deleteFarmlandServiceByIds(ids);
    }

    /**
     * 删除农田、服务关联信息
     *
     * @param id 农田、服务关联主键
     * @return 结果
     */
    @Override
    public int deleteFarmlandServiceById(String id)
    {
        return farmlandServiceMapper.deleteFarmlandServiceById(id);
    }
}
