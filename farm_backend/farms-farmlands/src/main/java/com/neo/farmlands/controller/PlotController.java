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
import com.neo.farmlands.domain.Plot;
import com.neo.farmlands.service.IPlotService;
import com.neo.common.utils.poi.ExcelUtil;
import com.neo.common.core.page.TableDataInfo;

/**
 * 农场地块Controller
 *
 * @author neo
 * @date 2024-04-16
 */
@RestController
@RequestMapping("/farmlands/plot")
public class PlotController extends BaseController
{
    @Autowired
    private IPlotService plotService;

    /**
     * 查询农场地块列表
     */
    @PreAuthorize("@ss.hasPermi('farmlands:plot:list')")
    @GetMapping("/list")
    public TableDataInfo list(Plot plot)
    {
        startPage();
        List<Plot> list = plotService.selectPlotList(plot);
        return getDataTable(list);
    }

    /**
     * 导出农场地块列表
     */
    @PreAuthorize("@ss.hasPermi('farmlands:plot:export')")
    @Log(title = "农场地块", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Plot plot)
    {
        List<Plot> list = plotService.selectPlotList(plot);
        ExcelUtil<Plot> util = new ExcelUtil<Plot>(Plot.class);
        util.exportExcel(response, list, "农场地块数据");
    }

    /**
     * 获取农场地块详细信息
     */
    @PreAuthorize("@ss.hasPermi('farmlands:plot:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(plotService.selectPlotById(id));
    }

    /**
     * 新增农场地块
     */
    @PreAuthorize("@ss.hasPermi('farmlands:plot:add')")
    @Log(title = "农场地块", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Plot plot)
    {
        return toAjax(plotService.insertPlot(plot));
    }

    /**
     * 修改农场地块
     */
    @PreAuthorize("@ss.hasPermi('farmlands:plot:edit')")
    @Log(title = "农场地块", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Plot plot)
    {
        return toAjax(plotService.updatePlot(plot));
    }

    /**
     * 删除农场地块
     */
    @PreAuthorize("@ss.hasPermi('farmlands:plot:remove')")
    @Log(title = "农场地块", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(plotService.deletePlotByIds(ids));
    }
}
