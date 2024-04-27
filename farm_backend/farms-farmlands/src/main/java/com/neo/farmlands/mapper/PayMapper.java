package com.neo.farmlands.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neo.farmlands.domain.entity.Pay;

/**
 * 支付记录Mapper接口
 *
 * @author neo
 * @date 2024-04-20
 */
public interface PayMapper extends BaseMapper<Pay>
{
    /**
     * 查询支付记录
     *
     * @param id 支付记录主键
     * @return 支付记录
     */
    public Pay selectPayById(String id);

    /**
     * 查询支付记录列表
     *
     * @param pay 支付记录
     * @return 支付记录集合
     */
    public List<Pay> selectPayList(Pay pay);

    /**
     * 新增支付记录
     *
     * @param pay 支付记录
     * @return 结果
     */
    public int insertPay(Pay pay);

    /**
     * 修改支付记录
     *
     * @param pay 支付记录
     * @return 结果
     */
    public int updatePay(Pay pay);

    /**
     * 删除支付记录
     *
     * @param id 支付记录主键
     * @return 结果
     */
    public int deletePayById(String id);

    /**
     * 批量删除支付记录
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePayByIds(String[] ids);
}
