package com.neo.farmlands.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neo.farmlands.domain.entity.FieldManagementRecordFiles;

import java.util.List;


/**
 * 田间管理记录附件Mapper接口
 *
 * @author neo
 * @date 2024-08-03
 */
public interface FieldManagementRecordFilesMapper extends BaseMapper<FieldManagementRecordFiles>
{
    /**
     * 查询田间管理记录附件
     *
     * @param id 田间管理记录附件主键
     * @return 田间管理记录附件
     */
    public FieldManagementRecordFiles selectFieldManagementRecordFilesById(String id);

    /**
     * 查询田间管理记录附件列表
     *
     * @param fieldManagementRecordFiles 田间管理记录附件
     * @return 田间管理记录附件集合
     */
    public List<FieldManagementRecordFiles> selectFieldManagementRecordFilesList(FieldManagementRecordFiles fieldManagementRecordFiles);

    /**
     * 新增田间管理记录附件
     *
     * @param fieldManagementRecordFiles 田间管理记录附件
     * @return 结果
     */
    public int insertFieldManagementRecordFiles(FieldManagementRecordFiles fieldManagementRecordFiles);

    /**
     * 修改田间管理记录附件
     *
     * @param fieldManagementRecordFiles 田间管理记录附件
     * @return 结果
     */
    public int updateFieldManagementRecordFiles(FieldManagementRecordFiles fieldManagementRecordFiles);

    /**
     * 删除田间管理记录附件
     *
     * @param id 田间管理记录附件主键
     * @return 结果
     */
    public int deleteFieldManagementRecordFilesById(String id);

    /**
     * 批量删除田间管理记录附件
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFieldManagementRecordFilesByIds(String[] ids);
}
