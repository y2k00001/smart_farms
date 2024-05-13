package com.neo.farmlands.controller.api;

import com.alibaba.fastjson.JSONObject;

import com.neo.common.core.domain.R;
import com.neo.farmlands.domain.entity.OrderPay;
import com.neo.farmlands.domain.vo.LesseeOrderVO;
import com.neo.farmlands.domain.vo.form.H5PreLesseeOrderForm;
import com.neo.farmlands.service.impl.H5OrderService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/h5/order")
@Slf4j
public class H5OrderController {


    @Resource
    private H5OrderService orderService;

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

}
