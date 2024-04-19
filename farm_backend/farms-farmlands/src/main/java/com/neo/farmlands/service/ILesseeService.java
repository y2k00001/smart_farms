package com.neo.farmlands.service;

import java.util.List;
import com.neo.farmlands.domain.Lessee;

/**
 * 租户信息Service接口
 * 
 * @author neo
 * @date 2024-04-19
 */
public interface ILesseeService 
{
    /**
     * 查询租户信息
     * 
     * @param id 租户信息主键
     * @return 租户信息
     */
    public Lessee selectLesseeById(String id);

    /**
     * 查询租户信息列表
     * 
     * @param lessee 租户信息
     * @return 租户信息集合
     */
    public List<Lessee> selectLesseeList(Lessee lessee);

    /**
     * 新增租户信息
     * 
     * @param lessee 租户信息
     * @return 结果
     */
    public int insertLessee(Lessee lessee);

    /**
     * 修改租户信息
     * 
     * @param lessee 租户信息
     * @return 结果
     */
    public int updateLessee(Lessee lessee);

    /**
     * 批量删除租户信息
     * 
     * @param ids 需要删除的租户信息主键集合
     * @return 结果
     */
    public int deleteLesseeByIds(String[] ids);

    /**
     * 删除租户信息信息
     * 
     * @param id 租户信息主键
     * @return 结果
     */
    public int deleteLesseeById(String id);
}
