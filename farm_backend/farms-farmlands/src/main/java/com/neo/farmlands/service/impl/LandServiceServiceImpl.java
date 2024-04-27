package com.neo.farmlands.service.impl;

import java.util.List;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neo.common.exception.ServiceException;
import com.neo.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.neo.farmlands.mapper.LandServiceMapper;
import com.neo.farmlands.domain.entity.LandService;
import com.neo.farmlands.service.ILandServiceService;

/**
 * 服务信息Service业务层处理
 *
 * @author neo
 * @date 2024-04-19
 */
@Service
public class LandServiceServiceImpl extends ServiceImpl<LandServiceMapper, LandService> implements ILandServiceService
{
    @Autowired
    private LandServiceMapper landServiceMapper;

    /**
     * 查询服务信息
     *
     * @param id 服务信息主键
     * @return 服务信息
     */
    @Override
    public LandService selectLandServiceById(String id)
    {
        return landServiceMapper.selectLandServiceById(id);
    }

    /**
     * 查询服务信息列表
     *
     * @param landService 服务信息
     * @return 服务信息
     */
    @Override
    public List<LandService> selectLandServiceList(LandService landService)
    {
        return landServiceMapper.selectLandServiceList(landService);
    }

    /**
     * 新增服务信息
     *
     * @param landService 服务信息
     * @return 结果
     */
    @Override
    public int insertLandService(LandService landService)
    {
        landService.setCreateTime(DateUtils.getNowDate());
        return landServiceMapper.insertLandService(landService);
    }

    /**
     * 修改服务信息
     *
     * @param landService 服务信息
     * @return 结果
     */
    @Override
    public int updateLandService(LandService landService)
    {
        landService.setUpdateTime(DateUtils.getNowDate());
        return landServiceMapper.updateLandService(landService);
    }

    /**
     * 批量删除服务信息
     *
     * @param ids 需要删除的服务信息主键
     * @return 结果
     */
    @Override
    public int deleteLandServiceByIds(String[] ids)
    {
        return landServiceMapper.deleteLandServiceByIds(ids);
    }

    /**
     * 删除服务信息信息
     *
     * @param id 服务信息主键
     * @return 结果
     */
    @Override
    public int deleteLandServiceById(String id)
    {
        return landServiceMapper.deleteLandServiceById(id);
    }

    @Override
    public LandService getOneByServiceId(String serviceId, Boolean isThrowException) {
        LambdaQueryWrapper<LandService> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(LandService::getServiceId, serviceId).eq(LandService::getIsDeleted, "0");
        LandService landService = this.getOne(queryWrapper);
        if(BeanUtil.isEmpty(landService) ){
            if(isThrowException){
                throw new ServiceException(StrUtil.format("编号【】服务不存在!",serviceId));
            }else {
                return new LandService();
            }
        }
        return landService;
    }
}
