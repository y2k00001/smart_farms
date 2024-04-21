package com.neo.farmlands.controller.manage;

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
import com.neo.farmlands.domain.FarmlandLesseeOrder;
import com.neo.farmlands.service.IFarmlandLesseeOrderService;
import com.neo.common.utils.poi.ExcelUtil;
import com.neo.common.core.page.TableDataInfo;

/**
 * 租赁信息和订单关联Controller
 *
 * @author neo
 * @date 2024-04-19
 */
@RestController
@RequestMapping("/farmlands/order")
public class FarmlandLesseeOrderController extends BaseController
{
    @Autowired
    private IFarmlandLesseeOrderService farmlandLesseeOrderService;

    /**
     * 查询租赁信息和订单关联列表
     */
    @PreAuthorize("@ss.hasPermi('farmlands:order:list')")
    @GetMapping("/list")
    public TableDataInfo list(FarmlandLesseeOrder farmlandLesseeOrder)
    {
        startPage();
        List<FarmlandLesseeOrder> list = farmlandLesseeOrderService.selectFarmlandLesseeOrderList(farmlandLesseeOrder);
        return getDataTable(list);
    }

    /**
     * 导出租赁信息和订单关联列表
     */
    @PreAuthorize("@ss.hasPermi('farmlands:order:export')")
    @Log(title = "租赁信息和订单关联", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FarmlandLesseeOrder farmlandLesseeOrder)
    {
        List<FarmlandLesseeOrder> list = farmlandLesseeOrderService.selectFarmlandLesseeOrderList(farmlandLesseeOrder);
        ExcelUtil<FarmlandLesseeOrder> util = new ExcelUtil<FarmlandLesseeOrder>(FarmlandLesseeOrder.class);
        util.exportExcel(response, list, "租赁信息和订单关联数据");
    }

    /**
     * 获取租赁信息和订单关联详细信息
     */
    @PreAuthorize("@ss.hasPermi('farmlands:order:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(farmlandLesseeOrderService.selectFarmlandLesseeOrderById(id));
    }

    /**
     * 新增租赁信息和订单关联
     */
    @PreAuthorize("@ss.hasPermi('farmlands:order:add')")
    @Log(title = "租赁信息和订单关联", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FarmlandLesseeOrder farmlandLesseeOrder)
    {
        return toAjax(farmlandLesseeOrderService.insertFarmlandLesseeOrder(farmlandLesseeOrder));
    }

    /**
     * 修改租赁信息和订单关联
     */
    @PreAuthorize("@ss.hasPermi('farmlands:order:edit')")
    @Log(title = "租赁信息和订单关联", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FarmlandLesseeOrder farmlandLesseeOrder)
    {
        return toAjax(farmlandLesseeOrderService.updateFarmlandLesseeOrder(farmlandLesseeOrder));
    }

    /**
     * 删除租赁信息和订单关联
     */
    @PreAuthorize("@ss.hasPermi('farmlands:order:remove')")
    @Log(title = "租赁信息和订单关联", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(farmlandLesseeOrderService.deleteFarmlandLesseeOrderByIds(ids));
    }
}
