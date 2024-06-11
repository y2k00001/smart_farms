package com.neo.farmlands.controller.manage;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.neo.farmlands.domain.entity.FieldManagementRecord;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.neo.common.annotation.Log;
import com.neo.common.core.controller.BaseController;
import com.neo.common.core.domain.AjaxResult;
import com.neo.common.enums.BusinessType;

import com.neo.farmlands.service.IFieldManagementRecordService;
import com.neo.common.utils.poi.ExcelUtil;
import com.neo.common.core.page.TableDataInfo;

/**
 * 田间管理记录Controller
 *
 * @author neo
 * @date 2024-06-11
 */
@RestController
@RequestMapping("/farmlands/record")
public class FieldManagementRecordController extends BaseController
{
    @Autowired
    private IFieldManagementRecordService fieldManagementRecordService;

    /**
     * 查询田间管理记录列表
     */
    @PreAuthorize("@ss.hasPermi('farmlands:record:list')")
    @GetMapping("/list")
    public TableDataInfo list(FieldManagementRecord fieldManagementRecord)
    {
        startPage();
        List<FieldManagementRecord> list = fieldManagementRecordService.selectFieldManagementRecordList(fieldManagementRecord);
        return getDataTable(list);
    }

    /**
     * 导出田间管理记录列表
     */
    @PreAuthorize("@ss.hasPermi('farmlands:record:export')")
    @Log(title = "田间管理记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FieldManagementRecord fieldManagementRecord)
    {
        List<FieldManagementRecord> list = fieldManagementRecordService.selectFieldManagementRecordList(fieldManagementRecord);
        ExcelUtil<FieldManagementRecord> util = new ExcelUtil<FieldManagementRecord>(FieldManagementRecord.class);
        util.exportExcel(response, list, "田间管理记录数据");
    }

    /**
     * 获取田间管理记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('farmlands:record:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(fieldManagementRecordService.selectFieldManagementRecordById(id));
    }

    /**
     * 新增田间管理记录
     */
    @PreAuthorize("@ss.hasPermi('farmlands:record:add')")
    @Log(title = "田间管理记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FieldManagementRecord fieldManagementRecord)
    {
        return toAjax(fieldManagementRecordService.insertFieldManagementRecord(fieldManagementRecord));
    }

    /**
     * 修改田间管理记录
     */
    @PreAuthorize("@ss.hasPermi('farmlands:record:edit')")
    @Log(title = "田间管理记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FieldManagementRecord fieldManagementRecord)
    {
        return toAjax(fieldManagementRecordService.updateFieldManagementRecord(fieldManagementRecord));
    }

    /**
     * 删除田间管理记录
     */
    @PreAuthorize("@ss.hasPermi('farmlands:record:remove')")
    @Log(title = "田间管理记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(fieldManagementRecordService.deleteFieldManagementRecordByIds(ids));
    }
}
