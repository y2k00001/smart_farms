package com.neo.farmlands.service.impl;

import java.util.List;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neo.common.exception.ServiceException;
import com.neo.common.utils.DateUtils;
import com.neo.farmlands.domain.entity.LandArea;
import com.neo.farmlands.domain.entity.StorageFiles;
import com.neo.farmlands.domain.vo.SeedVO;
import com.neo.farmlands.domain.vo.form.SeedForm;
import com.neo.farmlands.service.IStorageFilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.neo.farmlands.mapper.SeedMapper;
import com.neo.farmlands.domain.entity.Seed;
import com.neo.farmlands.service.ISeedService;

import javax.annotation.Resource;

/**
 * 种子信息Service业务层处理
 *
 * @author neo
 * @date 2024-04-16
 */
@Service
public class SeedServiceImpl extends ServiceImpl<SeedMapper, Seed> implements ISeedService
{
    @Resource
    private SeedMapper seedMapper;
    @Resource
    private IStorageFilesService storageFilesService;

    /**
     * 查询种子信息
     *
     * @param id 种子信息主键
     * @return 种子信息
     */
    @Override
    public SeedVO selectSeedById(String id)
    {
        SeedVO seedVO = new SeedVO();
        Seed seed = seedMapper.selectSeedById(id);
        if(BeanUtil.isNotEmpty(seed)){
            seedVO = BeanUtil.copyProperties(seed, SeedVO.class);
            List<StorageFiles> seedFiles = storageFilesService.listByFileIds(seedVO.getFileIds().split(","));
            seedVO.setFiles(seedFiles);
        }
        return seedVO;

    }

    /**
     * 查询种子信息列表
     *
     * @param seed 种子信息
     * @return 种子信息
     */
    @Override
    public List<Seed> selectSeedList(Seed seed)
    {
        return seedMapper.selectSeedList(seed);
    }

    /**
     * 新增种子信息
     *
     * @param seed 种子信息
     * @return 结果
     */
    @Override
    public int insertSeed(Seed seed)
    {
        seed.setCreateTime(DateUtils.getNowDate());
        return seedMapper.insertSeed(seed);
    }

    /**
     * 修改种子信息
     *
     * @param seed 种子信息
     * @return 结果
     */
    @Override
    public int updateSeed(Seed seed)
    {
        seed.setUpdateTime(DateUtils.getNowDate());
        return seedMapper.updateSeed(seed);
    }

    /**
     * 批量删除种子信息
     *
     * @param ids 需要删除的种子信息主键
     * @return 结果
     */
    @Override
    public int deleteSeedByIds(String[] ids)
    {
        return seedMapper.deleteSeedByIds(ids);
    }

    /**
     * 删除种子信息信息
     *
     * @param id 种子信息主键
     * @return 结果
     */
    @Override
    public int deleteSeedById(String id)
    {
        return seedMapper.deleteSeedById(id);
    }

    @Override
    public Seed getOneBySeedId(String seedId, Boolean isThrowException) {
        LambdaQueryWrapper<Seed> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Seed::getSeedId, seedId).eq(Seed::getIsDeleted, "0");
        Seed seed = this.getOne(queryWrapper);
        if(BeanUtil.isEmpty(seed) ){
            if(isThrowException){
                throw new ServiceException(StrUtil.format("编号【】种子不存在!",seedId));
            }else {
                return new Seed();
            }
        }
        return seed;
    }

    @Override
    public List<SeedVO> selectSeedListForH5(SeedForm seedForm) {
        List<SeedVO> seedVOList = seedMapper.selectSeedListForH5(seedForm);
        if(seedVOList.size()>0){
            seedVOList.forEach(seedVO -> {
                List<StorageFiles> seedFiles = storageFilesService.listByFileIds(seedVO.getFileIds().split(","));
                seedVO.setFiles(seedFiles);
            });
        }
        return seedVOList;
    }
}
