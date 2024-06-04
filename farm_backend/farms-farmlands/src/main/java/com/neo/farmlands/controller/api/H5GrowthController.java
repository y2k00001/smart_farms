package com.neo.farmlands.controller.api;

import com.neo.common.core.controller.BaseController;
import com.neo.common.core.domain.AjaxResult;
import com.neo.common.core.domain.R;
import com.neo.common.core.page.TableDataInfo;
import com.neo.farmlands.constant.BusinessConstant;
import com.neo.farmlands.domain.entity.Farmland;
import com.neo.farmlands.domain.entity.Growth;
import com.neo.farmlands.domain.entity.Member;
import com.neo.farmlands.domain.vo.FarmlandLesseeVO;
import com.neo.farmlands.domain.vo.FarmlandVO;
import com.neo.farmlands.domain.vo.GrowthVO;
import com.neo.farmlands.domain.vo.form.FarmlandLesseeForm;
import com.neo.farmlands.domain.vo.form.GrowthForm;
import com.neo.farmlands.service.IFarmlandService;
import com.neo.farmlands.service.IGrowthService;
import com.neo.framework.config.LocalDataUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 种植过程Controller
 *
 * @author neo
 * @date 2024-04-10
 */
@RestController
@RequestMapping("/h5/growth")
@Api(tags = {"H5种植过程信息"})
public class H5GrowthController extends BaseController
{
    @Autowired
    private IGrowthService growthService;


    /**
     * 查询我的种植过程信息列表
     */

    @PostMapping("/myGrowthList")
    @ApiOperation(value = "查询我的种植过程信息列表")
    public TableDataInfo<GrowthVO> myGrowthList(@RequestBody GrowthForm growthForm)
    {
        Member member = (Member) LocalDataUtil.getVar(BusinessConstant.MEMBER_INFO);
        Long memberId = member.getId();
        growthForm.setMemberId(memberId);
        startPage();
        List<GrowthVO> list = growthService.myGrowthList(growthForm);
        return getDataTable(list);
    }

    /**
     * 查询我的种植过程信息详情
     */

    @PostMapping("/myGrowthDetail")
    @ApiOperation(value = "查询我的种植过程信息详情")
    public R<GrowthVO> myFarmlandLesseeDetail(@RequestBody GrowthForm growthForm)
    {
        Member member = (Member) LocalDataUtil.getVar(BusinessConstant.MEMBER_INFO);
        Long memberId = member.getId();
        growthForm.setMemberId(memberId);
        GrowthVO growthVO = growthService.myFarmlandLesseeDetail(growthForm);
        return R.ok(growthVO);
    }

}
