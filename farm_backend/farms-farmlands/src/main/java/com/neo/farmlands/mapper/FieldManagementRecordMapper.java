package com.neo.farmlands.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neo.farmlands.domain.entity.FieldManagementRecord;

import java.util.List;


/**
 * 田间管理记录Mapper接口
 *
 * @author neo
 * @date 2024-06-11
 */
public interface FieldManagementRecordMapper extends BaseMapper<FieldManagementRecord>
{
    /**
     * 查询田间管理记录
     *
     * @param id 田间管理记录主键
     * @return 田间管理记录
     */
    public FieldManagementRecord selectFieldManagementRecordById(String id);

    /**
     * 查询田间管理记录列表
     *
     * @param fieldManagementRecord 田间管理记录
     * @return 田间管理记录集合
     */
    public List<FieldManagementRecord> selectFieldManagementRecordList(FieldManagementRecord fieldManagementRecord);

    /**
     * 新增田间管理记录
     *
     * @param fieldManagementRecord 田间管理记录
     * @return 结果
     */
    public int insertFieldManagementRecord(FieldManagementRecord fieldManagementRecord);

    /**
     * 修改田间管理记录
     *
     * @param fieldManagementRecord 田间管理记录
     * @return 结果
     */
    public int updateFieldManagementRecord(FieldManagementRecord fieldManagementRecord);

    /**
     * 删除田间管理记录
     *
     * @param id 田间管理记录主键
     * @return 结果
     */
    public int deleteFieldManagementRecordById(String id);

    /**
     * 批量删除田间管理记录
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFieldManagementRecordByIds(String[] ids);
}
