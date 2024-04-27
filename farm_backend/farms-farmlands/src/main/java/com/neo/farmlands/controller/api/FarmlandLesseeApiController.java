package com.neo.farmlands.controller.api;

import com.neo.common.annotation.Log;
import com.neo.common.core.controller.BaseController;
import com.neo.common.core.domain.R;
import com.neo.common.core.page.TableDataInfo;
import com.neo.common.enums.BusinessType;
import com.neo.common.utils.poi.ExcelUtil;
import com.neo.farmlands.domain.entity.FarmlandLessee;
import com.neo.farmlands.domain.vo.FarmlandLesseeReqVO;
import com.neo.farmlands.domain.vo.PayFarmlandLesseeReqVO;
import com.neo.farmlands.domain.vo.UnifiedPayRS;
import com.neo.farmlands.service.IFarmlandLesseeService;
import com.neo.farmlands.service.IPayService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 农田租赁信息Controller
 *
 * @author neo
 * @date 2024-04-15
 */
@RestController
@RequestMapping("/api/farmlands/lessee")
@Api(tags = {"Api-租赁合同信息"})
public class FarmlandLesseeApiController extends BaseController
{
    @Autowired
    private IFarmlandLesseeService farmlandLesseeService;

    @Resource
    private IPayService payService;


    /**
     * 提交农田租赁信息
     */

    @Log(title = "提交农田租赁信息", businessType = BusinessType.INSERT)
    @PostMapping("/submitFarmlandLessee")
    @ApiOperation(value = "提交农田租赁信息")
    public R submitFarmlandLessee(@RequestBody FarmlandLesseeReqVO farmlandLesseeReq)
    {
        farmlandLesseeService.submitFarmlandLessee(farmlandLesseeReq);
        return R.ok();
    }


    /**
     * 下单支付农田租赁合同
     */
    @PreAuthorize("@ss.hasPermi('farmlands:lessee:add')")
    @Log(title = "下单支付农田租赁合同", businessType = BusinessType.INSERT)
    @PostMapping("/prePayFarmlandLessee")
    @ApiOperation(value = "下单支付农田租赁合同")
    public R<UnifiedPayRS> prePayFarmlandLessee(@RequestBody PayFarmlandLesseeReqVO payFarmlandLesseeReqVO)
    {
        UnifiedPayRS unifiedPayRS =  payService.prePayFarmlandLessee(payFarmlandLesseeReqVO);
        return R.ok(unifiedPayRS);
    }


}
