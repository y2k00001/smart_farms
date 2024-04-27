package com.neo.farmlands.controller.api;

import com.neo.common.annotation.Log;
import com.neo.common.core.controller.BaseController;
import com.neo.common.core.domain.R;
import com.neo.common.core.page.TableDataInfo;
import com.neo.common.enums.BusinessType;
import com.neo.common.utils.poi.ExcelUtil;
import com.neo.farmlands.domain.entity.Farmland;
import com.neo.farmlands.service.IFarmlandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 农田信息Controller
 *
 * @author neo
 * @date 2024-04-10
 */
@RestController
@RequestMapping("/h5/farmland")
@Api(tags = {"农田信息"})
public class H5FarmlandController extends BaseController
{
    @Autowired
    private IFarmlandService farmlandService;

    /**
     * 查询农田信息列表
     */

    @GetMapping("/list")
    @ApiOperation(value = "查询农田信息列表")
    public R<TableDataInfo<Farmland>> list(Farmland farmland)
    {
        startPage();
        List<Farmland> list = farmlandService.selectFarmlandList(farmland);
        return R.ok(getDataTable(list));
    }

    /**
     * 获取农田信息详细信息
     */

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "获取农田信息详细信息")
    public R<Farmland> getInfo(@PathVariable("id") String id)
    {
        return R.ok(farmlandService.selectFarmlandById(id));
    }


}
