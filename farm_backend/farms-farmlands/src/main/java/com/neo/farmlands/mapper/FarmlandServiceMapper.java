package com.neo.farmlands.mapper;

import java.util.List;
import com.neo.farmlands.domain.FarmlandService;

/**
 * 农田、服务关联Mapper接口
 * 
 * @author neo
 * @date 2024-04-19
 */
public interface FarmlandServiceMapper 
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
     * 删除农田、服务关联
     * 
     * @param id 农田、服务关联主键
     * @return 结果
     */
    public int deleteFarmlandServiceById(String id);

    /**
     * 批量删除农田、服务关联
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFarmlandServiceByIds(String[] ids);
}
