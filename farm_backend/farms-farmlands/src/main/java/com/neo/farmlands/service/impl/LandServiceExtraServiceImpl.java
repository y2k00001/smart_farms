package com.neo.farmlands.service.impl;

import java.util.List;
import com.neo.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.neo.farmlands.mapper.LandServiceExtraMapper;
import com.neo.farmlands.domain.LandServiceExtra;
import com.neo.farmlands.service.ILandServiceExtraService;

/**
 * 服务信息扩展Service业务层处理
 *
 * @author neo
 * @date 2024-04-19
 */
@Service
public class LandServiceExtraServiceImpl implements ILandServiceExtraService
{
    @Autowired
    private LandServiceExtraMapper landServiceExtraMapper;

    /**
     * 查询服务信息扩展
     *
     * @param id 服务信息扩展主键
     * @return 服务信息扩展
     */
    @Override
    public LandServiceExtra selectLandServiceExtraById(String id)
    {
        return landServiceExtraMapper.selectLandServiceExtraById(id);
    }

    /**
     * 查询服务信息扩展列表
     *
     * @param landServiceExtra 服务信息扩展
     * @return 服务信息扩展
     */
    @Override
    public List<LandServiceExtra> selectLandServiceExtraList(LandServiceExtra landServiceExtra)
    {
        return landServiceExtraMapper.selectLandServiceExtraList(landServiceExtra);
    }

    /**
     * 新增服务信息扩展
     *
     * @param landServiceExtra 服务信息扩展
     * @return 结果
     */
    @Override
    public int insertLandServiceExtra(LandServiceExtra landServiceExtra)
    {
        landServiceExtra.setCreateTime(DateUtils.getNowDate());
        return landServiceExtraMapper.insertLandServiceExtra(landServiceExtra);
    }

    /**
     * 修改服务信息扩展
     *
     * @param landServiceExtra 服务信息扩展
     * @return 结果
     */
    @Override
    public int updateLandServiceExtra(LandServiceExtra landServiceExtra)
    {
        landServiceExtra.setUpdateTime(DateUtils.getNowDate());
        return landServiceExtraMapper.updateLandServiceExtra(landServiceExtra);
    }

    /**
     * 批量删除服务信息扩展
     *
     * @param ids 需要删除的服务信息扩展主键
     * @return 结果
     */
    @Override
    public int deleteLandServiceExtraByIds(String[] ids)
    {
        return landServiceExtraMapper.deleteLandServiceExtraByIds(ids);
    }

    /**
     * 删除服务信息扩展信息
     *
     * @param id 服务信息扩展主键
     * @return 结果
     */
    @Override
    public int deleteLandServiceExtraById(String id)
    {
        return landServiceExtraMapper.deleteLandServiceExtraById(id);
    }
}
