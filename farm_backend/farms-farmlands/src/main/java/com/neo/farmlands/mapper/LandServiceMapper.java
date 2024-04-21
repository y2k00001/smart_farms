package com.neo.farmlands.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neo.farmlands.domain.LandService;

/**
 * 服务信息Mapper接口
 *
 * @author neo
 * @date 2024-04-19
 */
public interface LandServiceMapper extends BaseMapper<LandService>
{
    /**
     * 查询服务信息
     *
     * @param id 服务信息主键
     * @return 服务信息
     */
    public LandService selectLandServiceById(String id);

    /**
     * 查询服务信息列表
     *
     * @param landService 服务信息
     * @return 服务信息集合
     */
    public List<LandService> selectLandServiceList(LandService landService);

    /**
     * 新增服务信息
     *
     * @param landService 服务信息
     * @return 结果
     */
    public int insertLandService(LandService landService);

    /**
     * 修改服务信息
     *
     * @param landService 服务信息
     * @return 结果
     */
    public int updateLandService(LandService landService);

    /**
     * 删除服务信息
     *
     * @param id 服务信息主键
     * @return 结果
     */
    public int deleteLandServiceById(String id);

    /**
     * 批量删除服务信息
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteLandServiceByIds(String[] ids);
}
