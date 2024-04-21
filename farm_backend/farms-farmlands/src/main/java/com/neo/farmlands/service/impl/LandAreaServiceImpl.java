package com.neo.farmlands.service.impl;

import java.util.List;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neo.common.exception.ServiceException;
import com.neo.common.utils.DateUtils;
import com.neo.farmlands.domain.Farmland;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.neo.farmlands.mapper.LandAreaMapper;
import com.neo.farmlands.domain.LandArea;
import com.neo.farmlands.service.ILandAreaService;

import javax.annotation.Resource;

/**
 * 农田租赁最小面积Service业务层处理
 *
 * @author neo
 * @date 2024-04-19
 */
@Service
public class LandAreaServiceImpl extends ServiceImpl<LandAreaMapper,LandArea> implements ILandAreaService
{
    @Resource
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

    @Override
    public LandArea getOneByLandAreaId(String landAreaId, Boolean isThrowException) {
        LambdaQueryWrapper<LandArea> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(LandArea::getLandAreaId, landAreaId).eq(LandArea::getIsDeleted, "0");
        LandArea landArea = this.getOne(queryWrapper);
        if(BeanUtil.isEmpty(landArea) ){
            if(isThrowException){
                throw new ServiceException(StrUtil.format("编号【】租赁地块不存在!",landAreaId));
            }else {
                return new LandArea();
            }
        }
        return landArea;
    }
}
