package com.neo.farmlands.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neo.farmlands.domain.entity.Seed;
import com.neo.farmlands.domain.vo.SeedVO;
import com.neo.farmlands.domain.vo.form.SeedForm;

/**
 * 种子信息Mapper接口
 *
 * @author neo
 * @date 2024-04-16
 */
public interface SeedMapper extends BaseMapper<Seed>
{
    /**
     * 查询种子信息
     *
     * @param id 种子信息主键
     * @return 种子信息
     */
    public Seed selectSeedById(String id);

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
     * @param seed 种子信息
     * @return 结果
     */
    public int insertSeed(Seed seed);

    /**
     * 修改种子信息
     *
     * @param seed 种子信息
     * @return 结果
     */
    public int updateSeed(Seed seed);

    /**
     * 删除种子信息
     *
     * @param id 种子信息主键
     * @return 结果
     */
    public int deleteSeedById(String id);

    /**
     * 批量删除种子信息
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSeedByIds(String[] ids);

    List<SeedVO> selectSeedListForH5(SeedForm seedForm);
}
