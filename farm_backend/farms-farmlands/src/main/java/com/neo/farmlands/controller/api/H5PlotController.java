package com.neo.farmlands.controller.api;

import com.neo.common.annotation.Log;
import com.neo.common.core.controller.BaseController;
import com.neo.common.core.domain.AjaxResult;
import com.neo.common.core.page.TableDataInfo;
import com.neo.common.enums.BusinessType;
import com.neo.common.utils.poi.ExcelUtil;
import com.neo.farmlands.domain.entity.Plot;
import com.neo.farmlands.service.IPlotService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 农场地块Controller
 *
 * @author neo
 * @date 2024-04-16
 */
@RestController
@RequestMapping("/h5/plot")
@Api(tags = {"h5农场地块"})
public class H5PlotController extends BaseController
{
    @Autowired
    private IPlotService plotService;

    /**
     * 查询农场地块列表
     */

    @GetMapping("/list")
    public TableDataInfo list(Plot plot)
    {
        startPage();
        List<Plot> list = plotService.selectPlotList(plot);
        return getDataTable(list);
    }


    /**
     * 获取农场地块详细信息
     */

    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(plotService.selectPlotById(id));
    }


}
