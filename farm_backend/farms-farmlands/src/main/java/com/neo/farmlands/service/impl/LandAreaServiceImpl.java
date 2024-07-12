package com.neo.farmlands.service.impl;
import java.math.BigDecimal;
import java.util.Date;
import com.google.common.collect.Maps;

import java.util.List;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neo.common.exception.ServiceException;
import com.neo.common.utils.DateUtils;
import com.neo.common.utils.IDGenerator;
import com.neo.common.utils.SecurityUtils;
import com.neo.farmlands.constant.IDConstants;
import com.neo.farmlands.domain.entity.Farmland;
import com.neo.farmlands.domain.entity.FarmlandArea;
import com.neo.farmlands.domain.vo.form.LandAreaForm;
import com.neo.farmlands.service.IFarmlandAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.neo.farmlands.mapper.LandAreaMapper;
import com.neo.farmlands.domain.entity.LandArea;
import com.neo.farmlands.service.ILandAreaService;
import org.springframework.transaction.annotation.Transactional;

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

    @Resource
    private IFarmlandAreaService farmlandAreaService;

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
     * @param landAreaForm 农田租赁最小面积
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertLandArea(LandAreaForm landAreaForm)
    {
        LandArea landArea = new LandArea();
        BeanUtil.copyProperties(landAreaForm, landArea);

        landArea.setLandAreaId(IDConstants.LAND_AREA_ID_PREFIX+ IDGenerator.generateId());
        landArea.setCreateByName(SecurityUtils.getUsername());
        landArea.setCreateBy(SecurityUtils.getUserId().toString());
        landArea.setCreateTime(DateUtils.getNowDate());

        this.save(landArea);

        FarmlandArea farmlandArea = new FarmlandArea();
        farmlandArea.setFarmlandId(landAreaForm.getFarmlandId());
        farmlandArea.setLandAreaId(landArea.getLandAreaId());
        farmlandArea.setCreateByName(SecurityUtils.getUsername());
        farmlandArea.setCreateBy(SecurityUtils.getUserId().toString());
        farmlandArea.setCreateTime(DateUtils.getNowDate());
        farmlandAreaService.save(farmlandArea);

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
