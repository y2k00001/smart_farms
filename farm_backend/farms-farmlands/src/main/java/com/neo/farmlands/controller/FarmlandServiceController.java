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
import com.neo.farmlands.domain.FarmlandService;
import com.neo.farmlands.service.IFarmlandServiceService;
import com.neo.common.utils.poi.ExcelUtil;
import com.neo.common.core.page.TableDataInfo;

/**
 * 农田、服务关联Controller
 *
 * @author neo
 * @date 2024-04-19
 */
@RestController
@RequestMapping("/farmlands/service")
public class FarmlandServiceController extends BaseController
{
    @Autowired
    private IFarmlandServiceService farmlandServiceService;

    /**
     * 查询农田、服务关联列表
     */
    @PreAuthorize("@ss.hasPermi('farmlands:service:list')")
    @GetMapping("/list")
    public TableDataInfo list(FarmlandService farmlandService)
    {
        startPage();
        List<FarmlandService> list = farmlandServiceService.selectFarmlandServiceList(farmlandService);
        return getDataTable(list);
    }

    /**
     * 导出农田、服务关联列表
     */
    @PreAuthorize("@ss.hasPermi('farmlands:service:export')")
    @Log(title = "农田、服务关联", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FarmlandService farmlandService)
    {
        List<FarmlandService> list = farmlandServiceService.selectFarmlandServiceList(farmlandService);
        ExcelUtil<FarmlandService> util = new ExcelUtil<FarmlandService>(FarmlandService.class);
        util.exportExcel(response, list, "农田、服务关联数据");
    }

    /**
     * 获取农田、服务关联详细信息
     */
    @PreAuthorize("@ss.hasPermi('farmlands:service:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(farmlandServiceService.selectFarmlandServiceById(id));
    }

    /**
     * 新增农田、服务关联
     */
    @PreAuthorize("@ss.hasPermi('farmlands:service:add')")
    @Log(title = "农田、服务关联", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FarmlandService farmlandService)
    {
        return toAjax(farmlandServiceService.insertFarmlandService(farmlandService));
    }

    /**
     * 修改农田、服务关联
     */
    @PreAuthorize("@ss.hasPermi('farmlands:service:edit')")
    @Log(title = "农田、服务关联", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FarmlandService farmlandService)
    {
        return toAjax(farmlandServiceService.updateFarmlandService(farmlandService));
    }

    /**
     * 删除农田、服务关联
     */
    @PreAuthorize("@ss.hasPermi('farmlands:service:remove')")
    @Log(title = "农田、服务关联", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(farmlandServiceService.deleteFarmlandServiceByIds(ids));
    }
}
