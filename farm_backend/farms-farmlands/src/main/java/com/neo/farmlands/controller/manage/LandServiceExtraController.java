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
import com.neo.farmlands.domain.entity.LandServiceExtra;
import com.neo.farmlands.service.ILandServiceExtraService;
import com.neo.common.utils.poi.ExcelUtil;
import com.neo.common.core.page.TableDataInfo;

/**
 * 服务信息扩展Controller
 *
 * @author neo
 * @date 2024-04-19
 */
@RestController
@RequestMapping("/farmlands/extra")
public class LandServiceExtraController extends BaseController
{
    @Autowired
    private ILandServiceExtraService landServiceExtraService;

    /**
     * 查询服务信息扩展列表
     */
    @PreAuthorize("@ss.hasPermi('farmlands:extra:list')")
    @GetMapping("/list")
    public TableDataInfo list(LandServiceExtra landServiceExtra)
    {
        startPage();
        List<LandServiceExtra> list = landServiceExtraService.selectLandServiceExtraList(landServiceExtra);
        return getDataTable(list);
    }

    /**
     * 导出服务信息扩展列表
     */
    @PreAuthorize("@ss.hasPermi('farmlands:extra:export')")
    @Log(title = "服务信息扩展", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, LandServiceExtra landServiceExtra)
    {
        List<LandServiceExtra> list = landServiceExtraService.selectLandServiceExtraList(landServiceExtra);
        ExcelUtil<LandServiceExtra> util = new ExcelUtil<LandServiceExtra>(LandServiceExtra.class);
        util.exportExcel(response, list, "服务信息扩展数据");
    }

    /**
     * 获取服务信息扩展详细信息
     */
    @PreAuthorize("@ss.hasPermi('farmlands:extra:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(landServiceExtraService.selectLandServiceExtraById(id));
    }

    /**
     * 新增服务信息扩展
     */
    @PreAuthorize("@ss.hasPermi('farmlands:extra:add')")
    @Log(title = "服务信息扩展", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody LandServiceExtra landServiceExtra)
    {
        return toAjax(landServiceExtraService.insertLandServiceExtra(landServiceExtra));
    }

    /**
     * 修改服务信息扩展
     */
    @PreAuthorize("@ss.hasPermi('farmlands:extra:edit')")
    @Log(title = "服务信息扩展", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody LandServiceExtra landServiceExtra)
    {
        return toAjax(landServiceExtraService.updateLandServiceExtra(landServiceExtra));
    }

    /**
     * 删除服务信息扩展
     */
    @PreAuthorize("@ss.hasPermi('farmlands:extra:remove')")
    @Log(title = "服务信息扩展", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(landServiceExtraService.deleteLandServiceExtraByIds(ids));
    }
}
