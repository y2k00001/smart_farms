package com.neo.farmlands.service.impl;
import java.util.Date;
import com.google.common.collect.Maps;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.neo.common.utils.IDGenerator;
import com.neo.farmlands.constant.BusinessConstant;
import com.neo.farmlands.domain.WechatPayData;
import com.neo.farmlands.domain.entity.*;
import com.neo.farmlands.domain.vo.LesseeOrderVO;
import com.neo.farmlands.domain.vo.OrderPayVO;
import com.neo.farmlands.domain.vo.form.H5PreLesseeOrderForm;
import com.neo.farmlands.domain.vo.form.OrderPayForm;
import com.neo.farmlands.domain.vo.form.OrderSubmitForm;
import com.neo.farmlands.mapper.FarmlandMapper;
import com.neo.farmlands.mapper.MemberAddressMapper;
import com.neo.farmlands.service.IStorageFilesService;
import com.neo.framework.config.LocalDataUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.math3.stat.descriptive.summary.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
public class H5OrderService {

    @Autowired
    private MemberAddressMapper memberAddressMapper;

    @Autowired
    private FarmlandMapper farmlandMapper;
    @Resource
    private IStorageFilesService storageFilesService;



    public void addOrderCheck() {

    }


    public LesseeOrderVO getPreOrder(H5PreLesseeOrderForm lesseeOrderForm) {
        LesseeOrderVO lesseeOrderVO = farmlandMapper.getFarmlandPreOrder(lesseeOrderForm);
        if(BeanUtil.isNotEmpty(lesseeOrderVO)){
            List<StorageFiles> storageFiles = storageFilesService.listByFileIds(lesseeOrderVO.getFileIds().split(","));
            lesseeOrderVO.setFiles(storageFiles);
        }
        return lesseeOrderVO;
    }

    @Transactional
    public Long submit(OrderSubmitForm form) {
        //1.查询构建所需的信息
        Member member = (Member) LocalDataUtil.getVar(BusinessConstant.MEMBER_INFO);

        // 2.构建农田租赁信息、租赁订单关联记录、租户信息表
        FarmlandLessee farmlandLessee = new FarmlandLessee();
        farmlandLessee.setId("");
        farmlandLessee.setFarmlandLesseeId("");
        farmlandLessee.setFarmlandId("");
        farmlandLessee.setLesseeId("");
        farmlandLessee.setLesseePrice(new BigDecimal("0"));
        farmlandLessee.setLesseeAmount(new BigDecimal("0"));
        farmlandLessee.setLesseeDay(0L);
        farmlandLessee.setLesseeStartDate(new Date());
        farmlandLessee.setLesseeEndDate(new Date());
        farmlandLessee.setStatus(0L);
        farmlandLessee.setLesseeFiles("");
        farmlandLessee.setCreateByName("");
        farmlandLessee.setIsDeleted(0L);
        farmlandLessee.setSearchValue("");
        farmlandLessee.setCreateBy("");
        farmlandLessee.setCreateTime(new Date());
        farmlandLessee.setUpdateBy("");
        farmlandLessee.setUpdateTime(new Date());
        farmlandLessee.setRemark("");
        farmlandLessee.setParams(Maps.newHashMap());

        // 3.将构建的记录插入数据库

        //计算商品总额、订单总额（订单总金额=商品总金额，因为暂时没有运费等概念）


        //生成一个统一的订单号
        Long orderId = IDGenerator.generateId();
        //生产一个payId
        Long payId = IDGenerator.generateId();

        return payId;
    }

    public OrderPayVO orderPay(OrderPayForm req) {

        OrderPayVO response = new OrderPayVO();

        return response;
    }
}
