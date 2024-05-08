package com.neo.farmlands.controller.api;

import com.neo.common.annotation.Log;
import com.neo.common.core.controller.BaseController;
import com.neo.common.core.domain.AjaxResult;
import com.neo.common.core.domain.R;
import com.neo.common.core.page.TableDataInfo;
import com.neo.common.enums.BusinessType;
import com.neo.common.utils.poi.ExcelUtil;
import com.neo.farmlands.domain.entity.Seed;
import com.neo.farmlands.domain.vo.SeedVO;
import com.neo.farmlands.domain.vo.form.SeedForm;
import com.neo.farmlands.service.ISeedService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 种子信息Controller
 *
 * @author neo
 * @date 2024-04-16
 */
@RestController
@RequestMapping("/h5/seed")
@Api(tags = "种子信息")
public class H5SeedController extends BaseController
{
    @Autowired
    private ISeedService seedService;

    /**
     * 查询种子信息列表
     */

    @GetMapping("/list")
    @ApiOperation(value = "查询种子信息列表")
    public TableDataInfo<SeedVO> list(SeedForm seedForm)
    {
        startPage();
        List<SeedVO> list = seedService.selectSeedListForH5(seedForm);
        return getDataTable(list);
    }



    /**
     * 获取种子信息详细信息
     */

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "获取种子信息详细信息")
    public R<Seed> getInfo(@PathVariable("id") String id)
    {
        return R.ok(seedService.selectSeedById(id));
    }


}
