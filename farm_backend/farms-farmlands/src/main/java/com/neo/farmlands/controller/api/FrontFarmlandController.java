package com.neo.farmlands.controller.api;

import com.neo.common.annotation.Anonymous;
import com.neo.common.core.controller.BaseController;
import com.neo.common.core.domain.R;
import com.neo.common.core.page.TableDataInfo;
import com.neo.farmlands.domain.entity.Farmland;
import com.neo.farmlands.domain.vo.FarmlandVO;
import com.neo.farmlands.service.IFarmlandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 农田信息Controller
 *
 * @author neo
 * @date 2024-04-10
 */
@RestController
@RequestMapping("/front/farmland")
@Api(tags = {"H5农田信息"})
public class FrontFarmlandController extends BaseController
{
    @Autowired
    private IFarmlandService farmlandService;

    /**
     * 查询农田信息列表
     */

    @GetMapping("/list")
    @ApiOperation(value = "查询农田信息列表")
    public TableDataInfo<FarmlandVO> list(Farmland farmland)
    {
        startPage();
        List<FarmlandVO> list = farmlandService.selectFarmlandList(farmland);
        return getDataTable(list);
    }

    /**
     * 获取农田信息详细信息
     */

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "获取农田信息详细信息")
    public R<FarmlandVO> getInfo(@PathVariable("id") String id)
    {
        return R.ok(farmlandService.selectFarmlandById(id));
    }


}
