package com.neo.farmlands.controller.api;

import com.neo.common.core.controller.BaseController;
import com.neo.common.core.domain.R;
import com.neo.common.core.page.TableDataInfo;
import com.neo.farmlands.domain.entity.Farmland;
import com.neo.farmlands.domain.entity.LandService;
import com.neo.farmlands.domain.vo.FarmlandVO;
import com.neo.farmlands.service.IFarmlandService;
import com.neo.farmlands.service.ILandServiceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 农田服务信息Controller
 *
 * @author neo
 * @date 2024-04-10
 */
@RestController
@RequestMapping("/h5/service")
@Api(tags = {"H5农田服务信息"})
public class H5LandServiceController extends BaseController
{
    @Autowired
    private IFarmlandService farmlandService;

    @Resource
    private ILandServiceService landServiceService;



    /**
     * 获取农田信息详细信息
     */

    @GetMapping(value = "/{serviceId}")
    @ApiOperation(value = "获取农田服务信息详细信息")
    public R<LandService> getInfo(@PathVariable("serviceId") String serviceId)
    {
        return R.ok(landServiceService.getOneByServiceId(serviceId,false));
    }

    /**
     * 获取农田服务列表详细信息
     */
    @GetMapping(value = "/farmlandService/{farmlandId}")
    @ApiOperation(value = "获取农田服务列表详细信息")
    public R<List<LandService>> getFarmLandList(@PathVariable("farmlandId") @NotNull String farmlandId)
    {
        return R.ok(landServiceService.getFarmLandList(farmlandId));
    }


}
