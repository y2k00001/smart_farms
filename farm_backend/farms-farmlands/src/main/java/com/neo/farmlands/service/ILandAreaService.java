package com.neo.farmlands.service;

import java.util.List;
import com.neo.farmlands.domain.LandArea;

/**
 * 农田租赁最小面积Service接口
 * 
 * @author neo
 * @date 2024-04-19
 */
public interface ILandAreaService 
{
    /**
     * 查询农田租赁最小面积
     * 
     * @param id 农田租赁最小面积主键
     * @return 农田租赁最小面积
     */
    public LandArea selectLandAreaById(String id);

    /**
     * 查询农田租赁最小面积列表
     * 
     * @param landArea 农田租赁最小面积
     * @return 农田租赁最小面积集合
     */
    public List<LandArea> selectLandAreaList(LandArea landArea);

    /**
     * 新增农田租赁最小面积
     * 
     * @param landArea 农田租赁最小面积
     * @return 结果
     */
    public int insertLandArea(LandArea landArea);

    /**
     * 修改农田租赁最小面积
     * 
     * @param landArea 农田租赁最小面积
     * @return 结果
     */
    public int updateLandArea(LandArea landArea);

    /**
     * 批量删除农田租赁最小面积
     * 
     * @param ids 需要删除的农田租赁最小面积主键集合
     * @return 结果
     */
    public int deleteLandAreaByIds(String[] ids);

    /**
     * 删除农田租赁最小面积信息
     * 
     * @param id 农田租赁最小面积主键
     * @return 结果
     */
    public int deleteLandAreaById(String id);
}
