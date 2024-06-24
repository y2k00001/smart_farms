package com.neo.farmlands.controller.api;

import com.alibaba.fastjson.JSONObject;

import com.egzosn.pay.common.exception.PayErrorException;
import com.neo.common.core.controller.BaseController;
import com.neo.common.core.domain.R;
import com.neo.common.core.page.TableDataInfo;
import com.neo.common.core.redis.RedisService;
import com.neo.common.exception.ServiceException;
import com.neo.farmlands.constant.BusinessConstant;
import com.neo.farmlands.domain.entity.*;
import com.neo.farmlands.domain.vo.*;
import com.neo.farmlands.domain.vo.form.FarmlandLesseeForm;
import com.neo.farmlands.domain.vo.form.H5PreLesseeOrderForm;
import com.neo.farmlands.domain.vo.form.OrderPayForm;
import com.neo.farmlands.domain.vo.form.OrderSubmitForm;
import com.neo.farmlands.service.impl.H5OrderService;
import com.neo.framework.config.LocalDataUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/h5/order")
@Slf4j
public class H5OrderController extends BaseController {


    @Resource
    private H5OrderService orderService;

    @Resource
    private RedisService redisService;


    @ApiOperation("订单列表")
    @GetMapping("/page")
    public TableDataInfo<H5OrderVO> orderPage(Integer status){
        startPage();
        Member member = (Member) LocalDataUtil.getVar(BusinessConstant.MEMBER_INFO);
        return getDataTable(orderService.orderPage(status, member.getId()));
    }

    @ApiOperation("获取预下单信息")
    @PostMapping("/addOrderCheck")
    public ResponseEntity<OrderPay> addOrderCheck(){
        orderService.addOrderCheck();
        OrderPay orderPay = new OrderPay();
        orderPay.setOrderId("2123123123");

        return ResponseEntity.ok(orderPay);
    }

    @ApiOperation("获取预下单信息")
    @PostMapping("/getPreOrder")
    public R<LesseeOrderVO> getPreOrder(@RequestBody H5PreLesseeOrderForm lesseeOrderForm){

        LesseeOrderVO lesseeOrderVO = orderService.getPreOrder(lesseeOrderForm);
        return R.ok(lesseeOrderVO);
    }

    @ApiOperation("下单")
    @PostMapping("/add")
    public R<LesseeOrderVO> submit(@RequestBody OrderSubmitForm form) {
        Member member = (Member) LocalDataUtil.getVar(BusinessConstant.MEMBER_INFO);
        Long memberId = member.getId();
        String redisKey = "h5_order_add" + memberId;
        String redisValue = memberId + "_" + System.currentTimeMillis();
        try{
            redisService.lock(redisKey, redisValue, 60);

            return R.ok(orderService.submit(form));
        }catch (Exception e){
            log.info("创建订单方法异常", e);
            throw new RuntimeException("服务繁忙，稍后再试");
        }finally {
            try {
                redisService.unLock(redisKey, redisValue);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @ApiOperation("订单支付")
    @PostMapping("/orderPay")
    public R<OrderPayVO> orderPay(@RequestBody OrderPayForm req){
        log.info("订单支付","提交的数据："+JSONObject.toJSONString(req));
        String redisKey = "h5_oms_order_pay_"+req.getOrderId();
        String redisValue = req.getOrderId()+"_"+System.currentTimeMillis();
        try {
            redisService.lock(redisKey, redisValue, 60);
            Member member = (Member) LocalDataUtil.getVar(BusinessConstant.MEMBER_INFO);
            Long memberId = member.getId();
            req.setMemberId(memberId);
            return R.ok(orderService.orderPay(req));
        }catch (PayErrorException payErrorException){
            log.error("支付异常", payErrorException);
            throw new ServiceException(payErrorException.getMessage());
        }
        catch (Exception e){
            log.error("支付方法异常", e);
            throw new RuntimeException(e.getMessage());
        }finally {
            try{
                redisService.unLock(redisKey,redisValue);
            }catch (Exception e){
                log.error("",e);
            }
        }
    }


    /**
     * 查询我的农田租赁信息列表
     */

    @PostMapping("/myFarmlandLesseeList")
    @ApiOperation(value = "查询我的农田租赁信息列表")
    public TableDataInfo<FarmlandLesseeVO> myFarmlandLesseeList(@RequestBody FarmlandLesseeForm farmlandLesseeForm)
    {
        Member member = (Member) LocalDataUtil.getVar(BusinessConstant.MEMBER_INFO);
        Long memberId = member.getId();
        farmlandLesseeForm.setMemberId(memberId);
        startPage();
        List<FarmlandLesseeVO> list = orderService.myFarmlandLesseeList(farmlandLesseeForm);
        return getDataTable(list);
    }

    /**
     * 查询我的农田租赁信息详情
     */

    @PostMapping("/myFarmlandLesseeDetail")
    @ApiOperation(value = "查询我的农田租赁信息详情")
    public R<FarmlandLesseeVO> myFarmlandLesseeDetail(@RequestBody FarmlandLesseeForm farmlandLesseeForm)
    {
        Member member = (Member) LocalDataUtil.getVar(BusinessConstant.MEMBER_INFO);
        Long memberId = member.getId();
        farmlandLesseeForm.setMemberId(memberId);
        FarmlandLesseeVO farmlandLesseeVO = orderService.myFarmlandLesseeDetail(farmlandLesseeForm);
        return R.ok(farmlandLesseeVO);
    }

}
