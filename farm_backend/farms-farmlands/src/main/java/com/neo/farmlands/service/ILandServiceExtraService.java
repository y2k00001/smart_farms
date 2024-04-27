package com.neo.farmlands.service;

import java.util.List;
import com.neo.farmlands.domain.entity.LandServiceExtra;

/**
 * 服务信息扩展Service接口
 *
 * @author neo
 * @date 2024-04-19
 */
public interface ILandServiceExtraService
{
    /**
     * 查询服务信息扩展
     *
     * @param id 服务信息扩展主键
     * @return 服务信息扩展
     */
    public LandServiceExtra selectLandServiceExtraById(String id);

    /**
     * 查询服务信息扩展列表
     *
     * @param landServiceExtra 服务信息扩展
     * @return 服务信息扩展集合
     */
    public List<LandServiceExtra> selectLandServiceExtraList(LandServiceExtra landServiceExtra);

    /**
     * 新增服务信息扩展
     *
     * @param landServiceExtra 服务信息扩展
     * @return 结果
     */
    public int insertLandServiceExtra(LandServiceExtra landServiceExtra);

    /**
     * 修改服务信息扩展
     *
     * @param landServiceExtra 服务信息扩展
     * @return 结果
     */
    public int updateLandServiceExtra(LandServiceExtra landServiceExtra);

    /**
     * 批量删除服务信息扩展
     *
     * @param ids 需要删除的服务信息扩展主键集合
     * @return 结果
     */
    public int deleteLandServiceExtraByIds(String[] ids);

    /**
     * 删除服务信息扩展信息
     *
     * @param id 服务信息扩展主键
     * @return 结果
     */
    public int deleteLandServiceExtraById(String id);
}
