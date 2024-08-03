package com.neo.farmlands.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neo.common.utils.DateUtils;
import com.neo.farmlands.domain.entity.FieldManagementRecordFiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.neo.farmlands.mapper.FieldManagementRecordFilesMapper;

import com.neo.farmlands.service.IFieldManagementRecordFilesService;

/**
 * 田间管理记录附件Service业务层处理
 *
 * @author neo
 * @date 2024-08-03
 */
@Service
public class FieldManagementRecordFilesServiceImpl extends ServiceImpl<FieldManagementRecordFilesMapper, FieldManagementRecordFiles> implements IFieldManagementRecordFilesService
{
    @Autowired
    private FieldManagementRecordFilesMapper fieldManagementRecordFilesMapper;

    /**
     * 查询田间管理记录附件
     *
     * @param id 田间管理记录附件主键
     * @return 田间管理记录附件
     */
    @Override
    public FieldManagementRecordFiles selectFieldManagementRecordFilesById(String id)
    {
        return fieldManagementRecordFilesMapper.selectFieldManagementRecordFilesById(id);
    }

    /**
     * 查询田间管理记录附件列表
     *
     * @param fieldManagementRecordFiles 田间管理记录附件
     * @return 田间管理记录附件
     */
    @Override
    public List<FieldManagementRecordFiles> selectFieldManagementRecordFilesList(FieldManagementRecordFiles fieldManagementRecordFiles)
    {
        return fieldManagementRecordFilesMapper.selectFieldManagementRecordFilesList(fieldManagementRecordFiles);
    }

    /**
     * 新增田间管理记录附件
     *
     * @param fieldManagementRecordFiles 田间管理记录附件
     * @return 结果
     */
    @Override
    public int insertFieldManagementRecordFiles(FieldManagementRecordFiles fieldManagementRecordFiles)
    {
        fieldManagementRecordFiles.setCreateTime(DateUtils.getNowDate());
        return fieldManagementRecordFilesMapper.insertFieldManagementRecordFiles(fieldManagementRecordFiles);
    }

    /**
     * 修改田间管理记录附件
     *
     * @param fieldManagementRecordFiles 田间管理记录附件
     * @return 结果
     */
    @Override
    public int updateFieldManagementRecordFiles(FieldManagementRecordFiles fieldManagementRecordFiles)
    {
        fieldManagementRecordFiles.setUpdateTime(DateUtils.getNowDate());
        return fieldManagementRecordFilesMapper.updateFieldManagementRecordFiles(fieldManagementRecordFiles);
    }

    /**
     * 批量删除田间管理记录附件
     *
     * @param ids 需要删除的田间管理记录附件主键
     * @return 结果
     */
    @Override
    public int deleteFieldManagementRecordFilesByIds(String[] ids)
    {
        return fieldManagementRecordFilesMapper.deleteFieldManagementRecordFilesByIds(ids);
    }

    /**
     * 删除田间管理记录附件信息
     *
     * @param id 田间管理记录附件主键
     * @return 结果
     */
    @Override
    public int deleteFieldManagementRecordFilesById(String id)
    {
        return fieldManagementRecordFilesMapper.deleteFieldManagementRecordFilesById(id);
    }
}
