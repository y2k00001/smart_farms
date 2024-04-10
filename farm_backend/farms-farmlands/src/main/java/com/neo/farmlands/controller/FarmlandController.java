package com.neo.farmlands.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
import com.neo.farmlands.domain.Farmland;
import com.neo.farmlands.service.IFarmlandService;
import com.neo.common.utils.poi.ExcelUtil;
import com.neo.common.core.page.TableDataInfo;

/**
 * 农田信息Controller
 *
 * @author neo
 * @date 2024-04-10
 */
@RestController
@RequestMapping("/farmlands/farmland")
public class FarmlandController extends BaseController
{
    @Autowired
    private IFarmlandService farmlandService;

    /**
     * 查询农田信息列表
     */
    @PreAuthorize("@ss.hasPermi('farmlands:farmland:list')")
    @GetMapping("/list")
    public TableDataInfo list(Farmland farmland)
    {
        startPage();
        List<Farmland> list = farmlandService.selectFarmlandList(farmland);
        return getDataTable(list);
    }

    /**
     * 导出农田信息列表
     */
    @PreAuthorize("@ss.hasPermi('farmlands:farmland:export')")
    @Log(title = "农田信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Farmland farmland)
    {
        List<Farmland> list = farmlandService.selectFarmlandList(farmland);
        ExcelUtil<Farmland> util = new ExcelUtil<Farmland>(Farmland.class);
        util.exportExcel(response, list, "农田信息数据");
    }

    /**
     * 获取农田信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('farmlands:farmland:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(farmlandService.selectFarmlandById(id));
    }

    /**
     * 新增农田信息
     */
    @PreAuthorize("@ss.hasPermi('farmlands:farmland:add')")
    @Log(title = "农田信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Farmland farmland)
    {
        return toAjax(farmlandService.insertFarmland(farmland));
    }

    /**
     * 修改农田信息
     */
    @PreAuthorize("@ss.hasPermi('farmlands:farmland:edit')")
    @Log(title = "农田信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Farmland farmland)
    {
        return toAjax(farmlandService.updateFarmland(farmland));
    }

    /**
     * 删除农田信息
     */
    @PreAuthorize("@ss.hasPermi('farmlands:farmland:remove')")
    @Log(title = "农田信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(farmlandService.deleteFarmlandByIds(ids));
    }
}
