package com.neo.farmlands.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neo.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.neo.farmlands.mapper.LesseeMapper;
import com.neo.farmlands.domain.Lessee;
import com.neo.farmlands.service.ILesseeService;

/**
 * 租户信息Service业务层处理
 *
 * @author neo
 * @date 2024-04-19
 */
@Service
public class LesseeServiceImpl extends ServiceImpl<LesseeMapper,Lessee> implements ILesseeService
{
    @Autowired
    private LesseeMapper lesseeMapper;

    /**
     * 查询租户信息
     *
     * @param id 租户信息主键
     * @return 租户信息
     */
    @Override
    public Lessee selectLesseeById(String id)
    {
        return lesseeMapper.selectLesseeById(id);
    }

    /**
     * 查询租户信息列表
     *
     * @param lessee 租户信息
     * @return 租户信息
     */
    @Override
    public List<Lessee> selectLesseeList(Lessee lessee)
    {
        return lesseeMapper.selectLesseeList(lessee);
    }

    /**
     * 新增租户信息
     *
     * @param lessee 租户信息
     * @return 结果
     */
    @Override
    public int insertLessee(Lessee lessee)
    {
        lessee.setCreateTime(DateUtils.getNowDate());
        return lesseeMapper.insertLessee(lessee);
    }

    /**
     * 修改租户信息
     *
     * @param lessee 租户信息
     * @return 结果
     */
    @Override
    public int updateLessee(Lessee lessee)
    {
        lessee.setUpdateTime(DateUtils.getNowDate());
        return lesseeMapper.updateLessee(lessee);
    }

    /**
     * 批量删除租户信息
     *
     * @param ids 需要删除的租户信息主键
     * @return 结果
     */
    @Override
    public int deleteLesseeByIds(String[] ids)
    {
        return lesseeMapper.deleteLesseeByIds(ids);
    }

    /**
     * 删除租户信息信息
     *
     * @param id 租户信息主键
     * @return 结果
     */
    @Override
    public int deleteLesseeById(String id)
    {
        return lesseeMapper.deleteLesseeById(id);
    }
}
