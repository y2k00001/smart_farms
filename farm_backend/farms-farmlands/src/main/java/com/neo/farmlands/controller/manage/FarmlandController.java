package com.neo.farmlands.controller.manage;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.neo.common.core.domain.R;
import com.neo.farmlands.domain.vo.FarmlandVO;
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
import com.neo.farmlands.domain.entity.Farmland;
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
@Api(tags = {"农田信息"})
public class FarmlandController extends BaseController
{
    @Autowired
    private IFarmlandService farmlandService;

    /**
     * 查询农田信息列表
     */
    @PreAuthorize("@ss.hasPermi('farmlands:farmland:list')")
    @GetMapping("/list")
    @ApiOperation(value = "查询农田信息列表")
    public TableDataInfo<FarmlandVO> list(Farmland farmland)
    {
        startPage();
        List<FarmlandVO> list = farmlandService.selectFarmlandList(farmland);
        return getDataTable(list);
    }

    /**
     * 导出农田信息列表
     */
    @PreAuthorize("@ss.hasPermi('farmlands:farmland:export')")
    @Log(title = "农田信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ApiOperation(value = "导出农田信息列表")
    public void export(HttpServletResponse response, Farmland farmland)
    {
        List<FarmlandVO> list = farmlandService.selectFarmlandList(farmland);
        ExcelUtil<FarmlandVO> util = new ExcelUtil<FarmlandVO>(FarmlandVO.class);
        util.exportExcel(response, list, "农田信息数据");
    }

    /**
     * 获取农田信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('farmlands:farmland:query')")
    @GetMapping(value = "/{id}")
    @ApiOperation(value = "获取农田信息详细信息")
    public R<Farmland> getInfo(@PathVariable("id") String id)
    {
        return R.ok(farmlandService.selectFarmlandById(id));
    }

    /**
     * 新增农田信息
     */
    @PreAuthorize("@ss.hasPermi('farmlands:farmland:add')")
    @Log(title = "农田信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ApiOperation(value = "新增农田信息")
    public R add(@RequestBody Farmland farmland)
    {
        farmlandService.insertFarmland(farmland);
        return R.ok();

    }

    /**
     * 修改农田信息
     */
    @PreAuthorize("@ss.hasPermi('farmlands:farmland:edit')")
    @Log(title = "农田信息", businessType = BusinessType.UPDATE)
    @PostMapping("/update")
    @ApiOperation(value = "修改农田信息")
    public R edit(@RequestBody Farmland farmland)
    {
        farmlandService.updateFarmland(farmland);
        return R.ok();
    }

    /**
     * 删除农田信息
     */
    @PreAuthorize("@ss.hasPermi('farmlands:farmland:remove')")
    @Log(title = "农田信息", businessType = BusinessType.DELETE)
	@PostMapping("/del/{ids}")
    @ApiOperation(value = "删除农田信息")
    public R remove(@PathVariable String[] ids)
    {
        farmlandService.deleteFarmlandByIds(ids);
        return R.ok();
    }
}
