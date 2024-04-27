package com.neo.farmlands.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neo.farmlands.domain.entity.LandArea;

/**
 * 农田租赁最小面积Mapper接口
 *
 * @author neo
 * @date 2024-04-19
 */
public interface LandAreaMapper extends BaseMapper<LandArea>
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
     * 删除农田租赁最小面积
     *
     * @param id 农田租赁最小面积主键
     * @return 结果
     */
    public int deleteLandAreaById(String id);

    /**
     * 批量删除农田租赁最小面积
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteLandAreaByIds(String[] ids);
}
