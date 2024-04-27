package com.neo.farmlands.controller.manage;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.neo.common.core.domain.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
import com.neo.farmlands.domain.entity.FarmlandLessee;
import com.neo.farmlands.service.IFarmlandLesseeService;
import com.neo.common.utils.poi.ExcelUtil;
import com.neo.common.core.page.TableDataInfo;

/**
 * 农田租赁信息Controller
 *
 * @author neo
 * @date 2024-04-15
 */
@RestController
@RequestMapping("/farmlands/lessee")
@Api(tags = {"租赁合同信息"})
public class FarmlandLesseeController extends BaseController
{
    @Autowired
    private IFarmlandLesseeService farmlandLesseeService;

    /**
     * 查询农田租赁信息列表
     */
    @PreAuthorize("@ss.hasPermi('farmlands:lessee:list')")
    @GetMapping("/list")
    @ApiOperation(value = "查询农田租赁信息列表")
    public TableDataInfo<FarmlandLessee> list(FarmlandLessee farmlandLessee)
    {
        startPage();
        List<FarmlandLessee> list = farmlandLesseeService.selectFarmlandLesseeList(farmlandLessee);
        return getDataTable(list);
    }

    /**
     * 导出农田租赁信息列表
     */
    @PreAuthorize("@ss.hasPermi('farmlands:lessee:export')")
    @Log(title = "农田租赁信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ApiOperation(value = "导出农田租赁信息列表")
    public void export(HttpServletResponse response, FarmlandLessee farmlandLessee)
    {
        List<FarmlandLessee> list = farmlandLesseeService.selectFarmlandLesseeList(farmlandLessee);
        ExcelUtil<FarmlandLessee> util = new ExcelUtil<FarmlandLessee>(FarmlandLessee.class);
        util.exportExcel(response, list, "农田租赁信息数据");
    }

    /**
     * 获取农田租赁信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('farmlands:lessee:query')")
    @GetMapping(value = "/{id}")
    @ApiOperation(value = "获取农田租赁信息详细信息")
    public R getInfo(@PathVariable("id") String id)
    {
        farmlandLesseeService.selectFarmlandLesseeById(id);
        return R.ok();
    }

    /**
     * 新增农田租赁信息
     */
    @PreAuthorize("@ss.hasPermi('farmlands:lessee:add')")
    @Log(title = "农田租赁信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ApiOperation(value = "新增农田租赁信息")
    public R add(@RequestBody FarmlandLessee farmlandLessee)
    {
        farmlandLesseeService.insertFarmlandLessee(farmlandLessee);
        return R.ok();
    }

    /**
     * 修改农田租赁信息
     */
    @PreAuthorize("@ss.hasPermi('farmlands:lessee:edit')")
    @Log(title = "农田租赁信息", businessType = BusinessType.UPDATE)
    @PostMapping("/update")
    @ApiOperation(value = "修改农田租赁信息")
    public R edit(@RequestBody FarmlandLessee farmlandLessee)
    {
       farmlandLesseeService.updateFarmlandLessee(farmlandLessee);
        return R.ok();
    }

    /**
     * 删除农田租赁信息
     */
    @PreAuthorize("@ss.hasPermi('farmlands:lessee:remove')")
    @Log(title = "农田租赁信息", businessType = BusinessType.DELETE)
	@PostMapping("/del/{ids}")
    @ApiOperation(value = "删除农田租赁信息")
    public R remove(@PathVariable String[] ids)
    {
        farmlandLesseeService.deleteFarmlandLesseeByIds(ids);
        return R.ok();
    }




}
