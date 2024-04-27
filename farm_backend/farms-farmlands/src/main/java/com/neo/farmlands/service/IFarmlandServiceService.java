package com.neo.farmlands.service;

import java.util.List;
import com.neo.farmlands.domain.entity.FarmlandService;

/**
 * 农田、服务关联Service接口
 *
 * @author neo
 * @date 2024-04-19
 */
public interface IFarmlandServiceService
{
    /**
     * 查询农田、服务关联
     *
     * @param id 农田、服务关联主键
     * @return 农田、服务关联
     */
    public FarmlandService selectFarmlandServiceById(String id);

    /**
     * 查询农田、服务关联列表
     *
     * @param farmlandService 农田、服务关联
     * @return 农田、服务关联集合
     */
    public List<FarmlandService> selectFarmlandServiceList(FarmlandService farmlandService);

    /**
     * 新增农田、服务关联
     *
     * @param farmlandService 农田、服务关联
     * @return 结果
     */
    public int insertFarmlandService(FarmlandService farmlandService);

    /**
     * 修改农田、服务关联
     *
     * @param farmlandService 农田、服务关联
     * @return 结果
     */
    public int updateFarmlandService(FarmlandService farmlandService);

    /**
     * 批量删除农田、服务关联
     *
     * @param ids 需要删除的农田、服务关联主键集合
     * @return 结果
     */
    public int deleteFarmlandServiceByIds(String[] ids);

    /**
     * 删除农田、服务关联信息
     *
     * @param id 农田、服务关联主键
     * @return 结果
     */
    public int deleteFarmlandServiceById(String id);
}
