package com.neo.farmlands.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.neo.farmlands.domain.entity.Seed;
import com.neo.farmlands.domain.vo.SeedVO;
import com.neo.farmlands.domain.vo.form.SeedForm;
import com.neo.farmlands.mapper.SeedMapper;

/**
 * 种子信息Service接口
 *
 * @author neo
 * @date 2024-04-16
 */
public interface ISeedService extends IService<Seed>
{
    /**
     * 查询种子信息
     *
     * @param id 种子信息主键
     * @return 种子信息
     */
    public SeedVO selectSeedById(String id);

    /**
     * 查询种子信息列表
     *
     * @param seed 种子信息
     * @return 种子信息集合
     */
    public List<Seed> selectSeedList(Seed seed);

    /**
     * 新增种子信息
     *
     * @param seedForm 种子信息
     * @return 结果
     */
    public void insertSeed(SeedForm seedForm);

    /**
     * 修改种子信息
     *
     * @param seed 种子信息
     * @return 结果
     */
    public int updateSeed(Seed seed);

    /**
     * 批量删除种子信息
     *
     * @param ids 需要删除的种子信息主键集合
     * @return 结果
     */
    public int deleteSeedByIds(String[] ids);

    /**
     * 删除种子信息信息
     *
     * @param id 种子信息主键
     * @return 结果
     */
    public int deleteSeedById(String id);

    Seed getOneBySeedId(String seedId, Boolean isThrowException);

    List<SeedVO> selectSeedListForH5(SeedForm seedForm);
}
