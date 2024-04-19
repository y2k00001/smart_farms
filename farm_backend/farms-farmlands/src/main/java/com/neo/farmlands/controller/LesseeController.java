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
import com.neo.farmlands.domain.Lessee;
import com.neo.farmlands.service.ILesseeService;
import com.neo.common.utils.poi.ExcelUtil;
import com.neo.common.core.page.TableDataInfo;

/**
 * 租户信息Controller
 *
 * @author neo
 * @date 2024-04-19
 */
@RestController
@RequestMapping("/farmlands/lessee")
public class LesseeController extends BaseController
{
    @Autowired
    private ILesseeService lesseeService;

    /**
     * 查询租户信息列表
     */
    @PreAuthorize("@ss.hasPermi('farmlands:lessee:list')")
    @GetMapping("/list")
    public TableDataInfo list(Lessee lessee)
    {
        startPage();
        List<Lessee> list = lesseeService.selectLesseeList(lessee);
        return getDataTable(list);
    }

    /**
     * 导出租户信息列表
     */
    @PreAuthorize("@ss.hasPermi('farmlands:lessee:export')")
    @Log(title = "租户信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Lessee lessee)
    {
        List<Lessee> list = lesseeService.selectLesseeList(lessee);
        ExcelUtil<Lessee> util = new ExcelUtil<Lessee>(Lessee.class);
        util.exportExcel(response, list, "租户信息数据");
    }

    /**
     * 获取租户信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('farmlands:lessee:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(lesseeService.selectLesseeById(id));
    }

    /**
     * 新增租户信息
     */
    @PreAuthorize("@ss.hasPermi('farmlands:lessee:add')")
    @Log(title = "租户信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Lessee lessee)
    {
        return toAjax(lesseeService.insertLessee(lessee));
    }

    /**
     * 修改租户信息
     */
    @PreAuthorize("@ss.hasPermi('farmlands:lessee:edit')")
    @Log(title = "租户信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Lessee lessee)
    {
        return toAjax(lesseeService.updateLessee(lessee));
    }

    /**
     * 删除租户信息
     */
    @PreAuthorize("@ss.hasPermi('farmlands:lessee:remove')")
    @Log(title = "租户信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(lesseeService.deleteLesseeByIds(ids));
    }
}
