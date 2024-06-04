package com.neo.farmlands.controller.manage;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.neo.common.core.domain.R;
import com.neo.farmlands.domain.entity.Growth;
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

import com.neo.farmlands.service.IGrowthService;
import com.neo.common.utils.poi.ExcelUtil;
import com.neo.common.core.page.TableDataInfo;

/**
 * 农作物生长周期信息Controller
 *
 * @author neo
 * @date 2024-06-03
 */
@RestController
@RequestMapping("/farmlands/growth")
public class GrowthController extends BaseController
{
    @Autowired
    private IGrowthService growthService;

    /**
     * 查询农作物生长周期信息列表
     */
    @PreAuthorize("@ss.hasPermi('farmlands:growth:list')")
    @GetMapping("/list")
    public TableDataInfo<Growth> list(Growth growth)
    {
        startPage();
        List<Growth> list = growthService.selectGrowthList(growth);
        return getDataTable(list);
    }

    /**
     * 导出农作物生长周期信息列表
     */
    @PreAuthorize("@ss.hasPermi('farmlands:growth:export')")
    @Log(title = "农作物生长周期信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Growth growth)
    {
        List<Growth> list = growthService.selectGrowthList(growth);
        ExcelUtil<Growth> util = new ExcelUtil<Growth>(Growth.class);
        util.exportExcel(response, list, "农作物生长周期信息数据");
    }

    /**
     * 获取农作物生长周期信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('farmlands:growth:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(growthService.selectGrowthById(id));
    }

    /**
     * 新增农作物生长周期信息
     */
    @PreAuthorize("@ss.hasPermi('farmlands:growth:add')")
    @Log(title = "农作物生长周期信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public R add(@RequestBody Growth growth)
    {
        return R.ok(growthService.insertGrowth(growth));
    }

    /**
     * 修改农作物生长周期信息
     */
    @PreAuthorize("@ss.hasPermi('farmlands:growth:edit')")
    @Log(title = "农作物生长周期信息", businessType = BusinessType.UPDATE)
    @PostMapping("/update")
    public AjaxResult edit(@RequestBody Growth growth)
    {
        return toAjax(growthService.updateGrowth(growth));
    }

    /**
     * 删除农作物生长周期信息
     */
    @PreAuthorize("@ss.hasPermi('farmlands:growth:remove')")
    @Log(title = "农作物生长周期信息", businessType = BusinessType.DELETE)
	@PostMapping("/del/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(growthService.deleteGrowthByIds(ids));
    }
}
