package com.neo.farmlands.controller.manage;

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
import com.neo.farmlands.domain.LandArea;
import com.neo.farmlands.service.ILandAreaService;
import com.neo.common.utils.poi.ExcelUtil;
import com.neo.common.core.page.TableDataInfo;

/**
 * 农田租赁最小面积Controller
 *
 * @author neo
 * @date 2024-04-19
 */
@RestController
@RequestMapping("/farmlands/area")
public class LandAreaController extends BaseController
{
    @Autowired
    private ILandAreaService landAreaService;

    /**
     * 查询农田租赁最小面积列表
     */
    @PreAuthorize("@ss.hasPermi('farmlands:area:list')")
    @GetMapping("/list")
    public TableDataInfo list(LandArea landArea)
    {
        startPage();
        List<LandArea> list = landAreaService.selectLandAreaList(landArea);
        return getDataTable(list);
    }

    /**
     * 导出农田租赁最小面积列表
     */
    @PreAuthorize("@ss.hasPermi('farmlands:area:export')")
    @Log(title = "农田租赁最小面积", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, LandArea landArea)
    {
        List<LandArea> list = landAreaService.selectLandAreaList(landArea);
        ExcelUtil<LandArea> util = new ExcelUtil<LandArea>(LandArea.class);
        util.exportExcel(response, list, "农田租赁最小面积数据");
    }

    /**
     * 获取农田租赁最小面积详细信息
     */
    @PreAuthorize("@ss.hasPermi('farmlands:area:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(landAreaService.selectLandAreaById(id));
    }

    /**
     * 新增农田租赁最小面积
     */
    @PreAuthorize("@ss.hasPermi('farmlands:area:add')")
    @Log(title = "农田租赁最小面积", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody LandArea landArea)
    {
        return toAjax(landAreaService.insertLandArea(landArea));
    }

    /**
     * 修改农田租赁最小面积
     */
    @PreAuthorize("@ss.hasPermi('farmlands:area:edit')")
    @Log(title = "农田租赁最小面积", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody LandArea landArea)
    {
        return toAjax(landAreaService.updateLandArea(landArea));
    }

    /**
     * 删除农田租赁最小面积
     */
    @PreAuthorize("@ss.hasPermi('farmlands:area:remove')")
    @Log(title = "农田租赁最小面积", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(landAreaService.deleteLandAreaByIds(ids));
    }
}
