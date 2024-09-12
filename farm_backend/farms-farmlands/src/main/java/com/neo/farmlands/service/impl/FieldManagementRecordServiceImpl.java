package com.neo.farmlands.service.impl;

import java.util.List;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neo.common.utils.DateUtils;
import com.neo.common.utils.uuid.IdUtils;
import com.neo.farmlands.constant.IDConstants;
import com.neo.farmlands.domain.entity.FieldManagementRecord;
import com.neo.farmlands.domain.vo.form.FieldManagementRecordForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.neo.farmlands.mapper.FieldManagementRecordMapper;

import com.neo.farmlands.service.IFieldManagementRecordService;

/**
 * 田间管理记录Service业务层处理
 *
 * @author neo
 * @date 2024-08-03
 */
@Service
public class FieldManagementRecordServiceImpl extends ServiceImpl<FieldManagementRecordMapper, FieldManagementRecord> implements IFieldManagementRecordService
{
    @Autowired
    private FieldManagementRecordMapper fieldManagementRecordMapper;

    /**
     * 查询田间管理记录
     *
     * @param id 田间管理记录主键
     * @return 田间管理记录
     */
    @Override
    public FieldManagementRecord selectFieldManagementRecordById(String id)
    {
        return fieldManagementRecordMapper.selectFieldManagementRecordById(id);
    }

    /**
     * 查询田间管理记录列表
     *
     * @param fieldManagementRecord 田间管理记录
     * @return 田间管理记录
     */
    @Override
    public List<FieldManagementRecord> selectFieldManagementRecordList(FieldManagementRecord fieldManagementRecord)
    {
        return fieldManagementRecordMapper.selectFieldManagementRecordList(fieldManagementRecord);
    }

    /**
     * 新增田间管理记录
     *
     * @param form 田间管理记录
     * @return 结果
     */
    @Override
    public int insertFieldManagementRecord(FieldManagementRecordForm form)
    {
        FieldManagementRecord fieldManagementRecord = new FieldManagementRecord();
        fieldManagementRecord.setCreateTime(DateUtils.getNowDate());
        fieldManagementRecord.setId(String.valueOf(IdUtils.getSnowflakeId()));
        fieldManagementRecord.setFieldManagementRecordId(IDConstants.MANAGE_RECORD_ID_PREFIX+IdUtils.getSnowflakeId());
        BeanUtil.copyProperties(form, fieldManagementRecord);
        return fieldManagementRecordMapper.insertFieldManagementRecord(fieldManagementRecord);
    }

    /**
     * 修改田间管理记录
     *
     * @param fieldManagementRecord 田间管理记录
     * @return 结果
     */
    @Override
    public int updateFieldManagementRecord(FieldManagementRecord fieldManagementRecord)
    {
        fieldManagementRecord.setUpdateTime(DateUtils.getNowDate());
        return fieldManagementRecordMapper.updateFieldManagementRecord(fieldManagementRecord);
    }

    /**
     * 批量删除田间管理记录
     *
     * @param ids 需要删除的田间管理记录主键
     * @return 结果
     */
    @Override
    public int deleteFieldManagementRecordByIds(String[] ids)
    {
        return fieldManagementRecordMapper.deleteFieldManagementRecordByIds(ids);
    }

    /**
     * 删除田间管理记录信息
     *
     * @param id 田间管理记录主键
     * @return 结果
     */
    @Override
    public int deleteFieldManagementRecordById(String id)
    {
        return fieldManagementRecordMapper.deleteFieldManagementRecordById(id);
    }
}
