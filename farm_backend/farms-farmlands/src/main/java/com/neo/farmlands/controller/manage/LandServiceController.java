package com.neo.farmlands.controller.manage;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.neo.common.core.domain.R;
import com.neo.farmlands.domain.vo.form.LandServiceForm;
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
import com.neo.farmlands.domain.entity.LandService;
import com.neo.farmlands.service.ILandServiceService;
import com.neo.common.utils.poi.ExcelUtil;
import com.neo.common.core.page.TableDataInfo;

/**
 * 服务信息Controller
 *
 * @author neo
 * @date 2024-04-19
 */
@RestController
@RequestMapping("/farmlands/service")
public class LandServiceController extends BaseController
{
    @Autowired
    private ILandServiceService landServiceService;

    /**
     * 查询服务信息列表
     */
    @PreAuthorize("@ss.hasPermi('farmlands:service:list')")
    @GetMapping("/list")
    public TableDataInfo list(LandService landService)
    {
        startPage();
        List<LandService> list = landServiceService.selectLandServiceList(landService);
        return getDataTable(list);
    }

    /**
     * 导出服务信息列表
     */
    @PreAuthorize("@ss.hasPermi('farmlands:service:export')")
    @Log(title = "服务信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, LandService landService)
    {
        List<LandService> list = landServiceService.selectLandServiceList(landService);
        ExcelUtil<LandService> util = new ExcelUtil<LandService>(LandService.class);
        util.exportExcel(response, list, "服务信息数据");
    }

    /**
     * 获取服务信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('farmlands:service:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(landServiceService.selectLandServiceById(id));
    }

    /**
     * 新增服务信息
     */
    @PreAuthorize("@ss.hasPermi('farmlands:service:add')")
    @Log(title = "服务信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public R add(@RequestBody LandServiceForm landService)
    {
        landServiceService.insertLandService(landService);
        return R.ok();
    }

    /**
     * 修改服务信息
     */
    @PreAuthorize("@ss.hasPermi('farmlands:service:edit')")
    @Log(title = "服务信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody LandService landService)
    {
        return toAjax(landServiceService.updateLandService(landService));
    }

    /**
     * 删除服务信息
     */
    @PreAuthorize("@ss.hasPermi('farmlands:service:remove')")
    @Log(title = "服务信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(landServiceService.deleteLandServiceByIds(ids));
    }
}
