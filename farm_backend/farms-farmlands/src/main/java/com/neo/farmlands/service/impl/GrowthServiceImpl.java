package com.neo.farmlands.service.impl;

import java.util.ArrayList;
import java.util.List;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neo.common.utils.DateUtils;
import com.neo.common.utils.IDGenerator;
import com.neo.common.utils.SecurityUtils;
import com.neo.common.utils.uuid.IdUtils;
import com.neo.farmlands.constant.BusinessConstant;
import com.neo.farmlands.constant.IDConstants;
import com.neo.farmlands.domain.entity.Growth;
import com.neo.farmlands.domain.vo.GrowthVO;
import com.neo.farmlands.domain.vo.form.FarmlandLesseeForm;
import com.neo.farmlands.domain.vo.form.GrowthForm;
import com.neo.farmlands.enums.FarmlandLesseeStatusEnum;
import com.neo.farmlands.enums.GrowthStatusEnum;
import com.neo.farmlands.service.IFarmlandLesseeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.neo.farmlands.mapper.GrowthMapper;
import com.neo.farmlands.service.IGrowthService;

import javax.annotation.Resource;

/**
 * 农作物生长周期信息Service业务层处理
 *
 * @author neo
 * @date 2024-06-03
 */
@Service
public class GrowthServiceImpl extends ServiceImpl<GrowthMapper, Growth> implements IGrowthService
{
    @Autowired
    private GrowthMapper growthMapper;

    @Resource
    private IFarmlandLesseeService farmlandLesseeService;

    /**
     * 查询农作物生长周期信息
     *
     * @param id 农作物生长周期信息主键
     * @return 农作物生长周期信息
     */
    @Override
    public Growth selectGrowthById(String id)
    {
        return growthMapper.selectGrowthById(id);
    }

    /**
     * 查询农作物生长周期信息列表
     *
     * @param growth 农作物生长周期信息
     * @return 农作物生长周期信息
     */
    @Override
    public List<Growth> selectGrowthList(Growth growth)
    {
        return growthMapper.selectGrowthList(growth);
    }

    /**
     * 新增农作物生长周期信息
     *
     * @param growth 农作物生长周期信息
     * @return 结果
     */
    @Override
    public int insertGrowth(Growth growth)
    {
        growth.setId(IdUtils.fastSimpleUUID());
        growth.setGrowthId(IDConstants.GROWTH_ID_PREFIX+IDGenerator.generateId());
        growth.setCreateBy(SecurityUtils.getUserId().toString());
        growth.setCreateByName(SecurityUtils.getUsername());
        growth.setCreateTime(DateUtils.getNowDate());
        return growthMapper.insertGrowth(growth);
    }

    /**
     * 修改农作物生长周期信息
     *
     * @param growth 农作物生长周期信息
     * @return 结果
     */
    @Override
    public int updateGrowth(Growth growth)
    {
        growth.setUpdateTime(DateUtils.getNowDate());
        return growthMapper.updateGrowth(growth);
    }

    /**
     * 批量删除农作物生长周期信息
     *
     * @param ids 需要删除的农作物生长周期信息主键
     * @return 结果
     */
    @Override
    public int deleteGrowthByIds(String[] ids)
    {
        return growthMapper.deleteGrowthByIds(ids);
    }

    /**
     * 删除农作物生长周期信息信息
     *
     * @param id 农作物生长周期信息主键
     * @return 结果
     */
    @Override
    public int deleteGrowthById(String id)
    {
        return growthMapper.deleteGrowthById(id);
    }

    @Override
    public List<GrowthVO> myGrowthList(GrowthForm growthForm) {
        if(BeanUtil.isEmpty(growthForm.getStatusList()) ||  !(growthForm.getStatusList().size() > 0) ){
            List<Long> statusList = new ArrayList<>();
            statusList.add(GrowthStatusEnum.GROWTH_STATUS_SEED.getCode());
            statusList.add(GrowthStatusEnum.GROWTH_STATUS_GROWING.getCode());
            statusList.add(GrowthStatusEnum.GROWTH_STATUS_HARVEST.getCode());
            statusList.add(GrowthStatusEnum.GROWTH_STATUS_FINISHED.getCode());
            growthForm.setStatusList(statusList);
        }

        List<GrowthVO> growthVOList = growthMapper.myGrowthList(growthForm);
        if(growthVOList.size()>0){
            growthVOList.forEach(growthVO -> {
                FarmlandLesseeForm farmlandLesseeForm = new FarmlandLesseeForm();
                farmlandLesseeForm.setFarmlandLesseeId(growthVO.getFarmlandLesseeId());
                farmlandLesseeForm.setMemberId(growthForm.getMemberId());
                farmlandLesseeService.myFarmlandLesseeDetail(farmlandLesseeForm);
            });
        }
        return growthVOList;
    }

    @Override
    public GrowthVO myFarmlandLesseeDetail(GrowthForm growthForm) {
        return null;
    }
}
