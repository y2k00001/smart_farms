package com.neo.farmlands.service.impl;

import java.util.List;
import com.neo.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.neo.farmlands.mapper.LandAreaMapper;
import com.neo.farmlands.domain.LandArea;
import com.neo.farmlands.service.ILandAreaService;

/**
 * 农田租赁最小面积Service业务层处理
 *
 * @author neo
 * @date 2024-04-19
 */
@Service
public class LandAreaServiceImpl implements ILandAreaService
{
    @Autowired
    private LandAreaMapper landAreaMapper;

    /**
     * 查询农田租赁最小面积
     *
     * @param id 农田租赁最小面积主键
     * @return 农田租赁最小面积
     */
    @Override
    public LandArea selectLandAreaById(String id)
    {
        return landAreaMapper.selectLandAreaById(id);
    }

    /**
     * 查询农田租赁最小面积列表
     *
     * @param landArea 农田租赁最小面积
     * @return 农田租赁最小面积
     */
    @Override
    public List<LandArea> selectLandAreaList(LandArea landArea)
    {
        return landAreaMapper.selectLandAreaList(landArea);
    }

    /**
     * 新增农田租赁最小面积
     *
     * @param landArea 农田租赁最小面积
     * @return 结果
     */
    @Override
    public int insertLandArea(LandArea landArea)
    {
        landArea.setCreateTime(DateUtils.getNowDate());
        return landAreaMapper.insertLandArea(landArea);
    }

    /**
     * 修改农田租赁最小面积
     *
     * @param landArea 农田租赁最小面积
     * @return 结果
     */
    @Override
    public int updateLandArea(LandArea landArea)
    {
        landArea.setUpdateTime(DateUtils.getNowDate());
        return landAreaMapper.updateLandArea(landArea);
    }

    /**
     * 批量删除农田租赁最小面积
     *
     * @param ids 需要删除的农田租赁最小面积主键
     * @return 结果
     */
    @Override
    public int deleteLandAreaByIds(String[] ids)
    {
        return landAreaMapper.deleteLandAreaByIds(ids);
    }

    /**
     * 删除农田租赁最小面积信息
     *
     * @param id 农田租赁最小面积主键
     * @return 结果
     */
    @Override
    public int deleteLandAreaById(String id)
    {
        return landAreaMapper.deleteLandAreaById(id);
    }
}
