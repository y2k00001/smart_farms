package com.neo.farmlands.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neo.farmlands.domain.FarmlandLessee;

/**
 * 农田租赁信息Mapper接口
 *
 * @author neo
 * @date 2024-04-15
 */
public interface FarmlandLesseeMapper extends BaseMapper<FarmlandLessee>
{
    /**
     * 查询农田租赁信息
     *
     * @param id 农田租赁信息主键
     * @return 农田租赁信息
     */
    public FarmlandLessee selectFarmlandLesseeById(String id);

    /**
     * 查询农田租赁信息列表
     *
     * @param farmlandLessee 农田租赁信息
     * @return 农田租赁信息集合
     */
    public List<FarmlandLessee> selectFarmlandLesseeList(FarmlandLessee farmlandLessee);

    /**
     * 新增农田租赁信息
     *
     * @param farmlandLessee 农田租赁信息
     * @return 结果
     */
    public int insertFarmlandLessee(FarmlandLessee farmlandLessee);

    /**
     * 修改农田租赁信息
     *
     * @param farmlandLessee 农田租赁信息
     * @return 结果
     */
    public int updateFarmlandLessee(FarmlandLessee farmlandLessee);

    /**
     * 删除农田租赁信息
     *
     * @param id 农田租赁信息主键
     * @return 结果
     */
    public int deleteFarmlandLesseeById(String id);

    /**
     * 批量删除农田租赁信息
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFarmlandLesseeByIds(String[] ids);
}
