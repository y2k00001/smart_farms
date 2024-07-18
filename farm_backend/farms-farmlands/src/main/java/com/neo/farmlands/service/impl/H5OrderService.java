package com.neo.farmlands.service.impl;
import java.time.ZoneId;
import java.util.Date;
import java.util.*;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.egzosn.pay.spring.boot.core.PayServiceManager;
import com.egzosn.pay.spring.boot.core.bean.MerchantPayOrder;

import com.egzosn.pay.wx.v3.bean.response.WxPayMessage;
import com.google.common.collect.Maps;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.neo.common.core.redis.RedisService;
import com.neo.common.exception.ServiceException;
import com.neo.common.utils.IDGenerator;
import com.neo.common.utils.uuid.IdUtils;
import com.neo.farmlands.constant.BusinessConstant;
import com.neo.farmlands.constant.IDConstants;
import com.neo.farmlands.domain.WechatPayData;
import com.neo.farmlands.domain.entity.*;
import com.neo.farmlands.domain.vo.FarmlandLesseeVO;
import com.neo.farmlands.domain.vo.LesseeOrderVO;
import com.neo.farmlands.domain.vo.OrderPayVO;
import com.neo.farmlands.domain.vo.PayVO;
import com.neo.farmlands.domain.vo.form.FarmlandLesseeForm;
import com.neo.farmlands.domain.vo.form.H5PreLesseeOrderForm;
import com.neo.farmlands.domain.vo.form.OrderPayForm;
import com.neo.farmlands.domain.vo.form.OrderSubmitForm;
import com.neo.farmlands.enums.*;
import com.neo.farmlands.mapper.FarmlandMapper;
import com.neo.farmlands.mapper.MemberAddressMapper;
import com.neo.farmlands.mapper.MemberWechatMapper;
import com.neo.farmlands.service.*;
import com.neo.framework.config.LocalDataUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.math3.stat.descriptive.summary.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import com.egzosn.pay.wx.v3.bean.response.order.TradeState;

@Service
@Slf4j
public class H5OrderService {

    @Resource
    private MemberAddressMapper memberAddressMapper;
    @Resource
    private FarmlandMapper farmlandMapper;
    @Resource
    private IStorageFilesService storageFilesService;
    @Resource
    private ILesseeService lesseeService;
    @Resource
    private IFarmlandLesseeService farmlandLesseeService;
    @Resource
    private IFarmlandLesseeOrderService farmlandLesseeOrderService;
    @Resource
    private IFarmlandLesseeSeedService farmlandLesseeSeedService;
    @Resource
    private IPayService payService;
    @Resource
    private IOrderPayService orderPayService;

    @Autowired
    private PayServiceManager manager;
    @Autowired
    private MemberWechatMapper memberWechatMapper;

    @Resource
    private RedisService redisService;

    @Resource
    private IFarmlandAreaService farmlandAreaService;

    @Resource
    private ILandAreaService landAreaService;

    @Resource
    private IFarmlandService farmlandService;;

    @Resource
    private IGrowthService growthService;


    public void addOrderCheck() {

    }


    public LesseeOrderVO getPreOrder(H5PreLesseeOrderForm lesseeOrderForm) {
        LesseeOrderVO lesseeOrderVO = farmlandMapper.getFarmlandPreOrder(lesseeOrderForm);
        if(BeanUtil.isNotEmpty(lesseeOrderVO)){
            List<StorageFiles> storageFiles = farmlandService.getStorageFiles(lesseeOrderVO.getFileIds());
            lesseeOrderVO.setFiles(storageFiles);
        }
        return lesseeOrderVO;
    }

    @Transactional
    public LesseeOrderVO submit(OrderSubmitForm form) {
        LesseeOrderVO lesseeOrderVO = new LesseeOrderVO();
        //1.查询构建所需的信息
        Member member = (Member) LocalDataUtil.getVar(BusinessConstant.MEMBER_INFO);
        Farmland farmland = farmlandMapper.selectFarmlandByFarmlandId(form.getFarmlandId());
        //生成一个统一的订单号
        Long orderId = IDGenerator.generateId();

        // 2.1构建租户信息表
        Lessee lessee = new Lessee();
        lessee.setLesseeId(IDConstants.LESSEE_ID_PREFIX+ IdUtils.fastSimpleUUID());
        lessee.setUserId(member.getId().toString());
        lessee.setLesseeName(form.getLesseeName());
        lessee.setLesseePhone(form.getLesseePhone());
        lessee.setRemark(form.getLesseeRemark());
        lessee.setCreateByName("system");
        // 2.2将构建的记录插入数据库
        lesseeService.save(lessee);



        // 3.1构建农田租赁信息、农田租赁种子关联表
        FarmlandLessee farmlandLessee = new FarmlandLessee();
        farmlandLessee.setFarmlandLesseeId(IDConstants.FARMLAND_LESSEE_ID_PREFIX+ IdUtils.fastSimpleUUID());
        farmlandLessee.setFarmlandId(farmland.getFarmlandId());
        farmlandLessee.setLandAreaId(form.getLandAreaId());
        farmlandLessee.setServiceId(form.getServiceId());
        farmlandLessee.setLesseeId(lessee.getLesseeId());
        farmlandLessee.setMemberId(member.getId());
        farmlandLessee.setLesseePrice(farmland.getLeasePrice());
        farmlandLessee.setLesseeAmount(new BigDecimal(form.getOrderAmount()));
        farmlandLessee.setLesseeDay(form.getLesseeDay());
        farmlandLessee.setStatus(0L);
        farmlandLessee.setCreateByName("system");
        farmlandLessee.setRemark("预下单");

        List<FarmlandLesseeSeed> farmlandLesseeSeeds = new ArrayList<>();
        if(CollectionUtil.isNotEmpty(form.getSeedIds())){
            form.getSeedIds().forEach(seedId -> {
                FarmlandLesseeSeed farmlandLesseeSeed = new FarmlandLesseeSeed();
                farmlandLesseeSeed.setFarmlandLesseeId(farmlandLessee.getFarmlandLesseeId());
                farmlandLesseeSeed.setSeedId(seedId);
                farmlandLesseeSeed.setCreateByName("system");
                farmlandLesseeSeeds.add(farmlandLesseeSeed);
            });
            farmlandLesseeSeedService.saveBatch(farmlandLesseeSeeds);
        }


        // 3.2将构建的记录插入数据库
        farmlandLesseeService.save(farmlandLessee);


        // 4.1构建租赁订单关联记录
        FarmlandLesseeOrder farmlandLesseeOrder = new FarmlandLesseeOrder();
        farmlandLesseeOrder.setFarmlandLesseeId(farmlandLessee.getFarmlandLesseeId());
        farmlandLesseeOrder.setOrderId(orderId.toString());
        farmlandLesseeOrder.setCreateByName("system");
        // 4.2将构建的记录插入数据库
        farmlandLesseeOrderService.save(farmlandLesseeOrder);

        lesseeOrderVO.setOrderId(orderId.toString());
        lesseeOrderVO.setFarmlandLesseeId(farmlandLessee.getFarmlandLesseeId());
        return lesseeOrderVO;
    }

    public OrderPayVO orderPay(OrderPayForm req) {
        OrderPayVO response = new OrderPayVO();
        // 1.根据orderId查询租赁信息
        FarmlandLessee farmlandLessee = farmlandLesseeOrderService.getOneByOrderId(req.getOrderId());
        if (BeanUtil.isEmpty(farmlandLessee)) {
            throw new ServiceException("没有待支付的订单");
        }
        // 2.查询订单、支付关联表，有就更新pay记录，没有就新插入pay记录
        PayVO payVO = orderPayService.getByOrderId(req.getOrderId());
        String payId;
        Pay pay = new Pay();
        if(BeanUtil.isEmpty(payVO)){
            //生成一个统一的订单号
            payId = String.valueOf(IDGenerator.generateId());
            // 3.创建支付记录表、订单支付记录关联表

            pay.setPayId(payId);
            String subject = StrUtil.format("微信小程序缴费-租赁");
            pay.setSubject(subject);
            pay.setBody(subject);
            pay.setPaySource(10);
            pay.setPayChannel(10);
            pay.setPayAmount(farmlandLessee.getLesseeAmount());
            pay.setUpdateAmount(new BigDecimal("0"));
            pay.setPayStatus(0);
            pay.setExpiredTime(DateUtil.offsetMinute(new Date(),30));
            payService.save(pay);
            OrderPay orderPay = new OrderPay();
            orderPay.setOrderId(req.getOrderId().toString());
            orderPay.setOrderType(OrderTypeEnum.ORDER_TYPE_FARMLAND_LESSEE.getCode());
            orderPay.setPayId(pay.getPayId());
            orderPayService.save(orderPay);
        }else {
            payId = payVO.getPayId();
            Pay updatePay = BeanUtil.copyProperties(payVO,Pay.class);
            updatePay.setPayAmount(farmlandLessee.getLesseeAmount());
            updatePay.setPayStatus(0);
            updatePay.setExpiredTime(DateUtil.offsetMinute(new Date(),30));
            updatePay.setUpdateTime(new Date());

            payService.updateByPayId(updatePay.getPayId(),updatePay);
            pay = payService.getPayByPayId(payId);
        }


        // 4.调用第三方支付接口
        Map<String,Object> resultMap = goH5pay(pay,req);

        // 5.更新支付记录表
        payService.updateStatusByPayId(payId, PayStateEnum.PAY_STATE_PAYING.getCode());
        // 6.返回支付参数信息
        response.setAppId(resultMap.get("appId").toString());
        response.setTimeStamp(resultMap.get("timeStamp").toString());
        response.setNonceStr(resultMap.get("nonceStr").toString());
        response.setSignType(resultMap.get("signType").toString());
        response.setPackage_(resultMap.get("package").toString());
        response.setPaySign(resultMap.get("paySign").toString());



        return response;
    }

    private Map<String, Object> goH5pay(Pay pay,OrderPayForm req) {
        QueryWrapper<MemberWechat> memberWechatQw = new QueryWrapper<>();
        memberWechatQw.eq("member_id", req.getMemberId());
        MemberWechat memberWechat = memberWechatMapper.selectOne(memberWechatQw);
        if (memberWechat == null ) {
            throw new RuntimeException("获取用户微信信息失败");
        }

        String openId = memberWechat.getOpenid();
        if (2 == req.getWechatType()) {
            openId = memberWechat.getRoutineOpenid();
        }
        String detailsId = "4";
        String transactionType = "JSAPI";
        String subject = pay.getSubject();
        String body = pay.getBody();
        BigDecimal amount = pay.getPayAmount();
        String outTradeNo = pay.getPayId();
        //获取对应的支付账户操作工具（可根据账户id）
        MerchantPayOrder payOrder = new MerchantPayOrder(detailsId, transactionType, subject, body, new BigDecimal("0.01"), outTradeNo);
        payOrder.setOpenid(openId);
        return manager.getOrderInfo(payOrder);
    }

    public List<?> orderPage(Integer status, Long id) {
        return null;
    }

    public List<FarmlandLesseeVO> myFarmlandLesseeList(FarmlandLesseeForm farmlandLesseeForm) {
        return farmlandLesseeService.myFarmlandLesseeList(farmlandLesseeForm);
    }

    public FarmlandLesseeVO myFarmlandLesseeDetail(FarmlandLesseeForm farmlandLesseeForm) {
        return farmlandLesseeService.myFarmlandLesseeDetail(farmlandLesseeForm);
    }

    /**
     * 微信支付回调通知的业务处理
     * @author monkey
     * @datetime  2024/6/23 00:29
     * @param payMessage
     * @return
     **/
    @Transactional
    public void payCallBack(WxPayMessage payMessage) {
        log.info("【订单支付回调】" + JSONObject.toJSON(payMessage));
        String redisKey = "h5_oms_order_pay_notify_" + payMessage.getOutTradeNo();
        String redisValue = payMessage.getOutTradeNo() + "_" + System.currentTimeMillis();
        LocalDateTime optDate = LocalDateTime.now();
        try {
            redisService.lock(redisKey, redisValue, 60);
            //1.先判断微信回调的是否未success

            if (!TradeState.SUCCESS.equals(payMessage.getTradeState())) {
                log.error("【订单支付回调】订单状态不是支付成功状态" + payMessage.getTradeState());
                throw new RuntimeException();
            }
            // 2.修改支付记录
            updatePay(payMessage);
            // 3.修改订单记录
            updateOrder(payMessage);
            // 4.修改租赁记录
            FarmlandLessee farmlandLessee = updateFarmlandLessee(payMessage);
            // 5.修改农田租赁最小面积地块记录
            updateLandArea(farmlandLessee);
            // 6.创建作物生长周期信息记录
            createGrowth(farmlandLessee);

        } catch (Exception e) {
            log.error("订单支付回调异常", e);
            throw new RuntimeException("订单支付回调异常");
        } finally {
            try {
                redisService.unLock(redisKey, redisValue);
            } catch (Exception e) {
                log.error("", e);
            }
        }

    }

    private void createGrowth(FarmlandLessee farmlandLessee) {
        List<Seed> seedList = farmlandLesseeSeedService.getSeedListByFarmlandLesseeId(farmlandLessee.getFarmlandLesseeId());
        Seed seed = seedList.get(0);

        LandArea landArea = landAreaService.getOneByLandAreaId(farmlandLessee.getLandAreaId(), true);
        Farmland farmland = farmlandService.getOneByFarmlandId(farmlandLessee.getFarmlandId(), true);
        Growth growth = new Growth();

        growth.setGrowthId(IDConstants.GROWTH_ID_PREFIX + IdUtils.fastSimpleUUID());
        growth.setFarmlandId(farmlandLessee.getFarmlandId());
        growth.setFarmlandLesseeId(farmlandLessee.getFarmlandLesseeId());
        growth.setSeedId(seed.getSeedId());
        growth.setLandAreaId(landArea.getLandAreaId());
        growth.setFarmlandSnap(JSONObject.toJSONString(farmland));
        growth.setSeedSnap(JSONObject.toJSONString(seed));
        growth.setFarmlandName(farmland.getFarmlandName());
        growth.setCropName(seed.getSeedName());
        growth.setGrowthStage(Convert.toInt(GrowthStatusEnum.GROWTH_STATUS_SEED.getCode()));
        growth.setGrowthTime(String.valueOf(seed.getGrowthCycle()));
        growth.setStartDate(DateUtil.now());

        growth.setGrowthConditions(seed.getSeedtime());
        growth.setClimateSuitability(seed.getClimate());

        growth.setYieldExpectation(seed.getYield());
        growth.setVarietyDescription(seed.getPlantingTechnique());
        growth.setRemarks("支付完成创建档案");
        growth.setIsDeleted(0);
        growth.setCreateTime(new Date());

        growthService.save(growth);


    }

    private void updateLandArea(FarmlandLessee farmlandLessee) {

        LandArea landArea = landAreaService.getOneByLandAreaId(farmlandLessee.getLandAreaId(), true);
        landArea.setLesseeStatus(LesseeStatusEnum.LESSEE_STATUS_YES.getCode());
        landArea.setUpdateTime(DateUtil.parse(DateUtil.now()));
        landAreaService.updateById(landArea);
    }

    private FarmlandLessee updateFarmlandLessee(WxPayMessage payMessage) {
        String payId = payMessage.getOutTradeNo();
        OrderPay orderPay = orderPayService.getByPayId(payId);
        FarmlandLessee farmlandLessee   = farmlandLesseeOrderService.getOneByOrderId(orderPay.getOrderId());
        farmlandLessee.setLesseeStartDate(DateUtil.tomorrow());
        farmlandLessee.setLesseeEndDate(DateUtil.offsetDay(farmlandLessee.getLesseeStartDate(), Convert.toInt(farmlandLessee.getLesseeDay())));
        farmlandLessee.setStatus(FarmlandLesseeStatusEnum.FARMLAND_LESSEE_STATUS_PLANTING.getCode());
        farmlandLessee.setUpdateTime(DateUtil.parse(DateUtil.now()));
        farmlandLesseeService.updateById(farmlandLessee);
        return farmlandLesseeService.getById(farmlandLessee.getId());
    }

    private void updateOrder(WxPayMessage payMessage) {
        String payId = payMessage.getOutTradeNo();
        Pay pay = payService.getPayByPayId(payId);
        OrderPay orderPay = orderPayService.getByPayId(payId);
        FarmlandLesseeOrder lesseeOrder  = farmlandLesseeOrderService.getLesseeOrderByOrderId(orderPay.getOrderId());
        //突然发现没有什么需要更新的
    }

    private void updatePay(WxPayMessage payMessage) {
        String payId = payMessage.getOutTradeNo();
        Pay pay = payService.getPayByPayId(payId);
        pay.setThirdTradeNo(payMessage.getTransactionId());
        pay.setPayUserId("wx_"+payMessage.getPayer().getOpenid());
        pay.setPayChannel(PayChannelEnum.PAY_CHANNEL_WXPAY.getCode());
        pay.setPayAmount(payMessage.getTotalFee());
        pay.setPayStatus(PayStateEnum.PAY_STATE_PAY_SUCCESS.getCode());
        pay.setPayTime(payMessage.getSuccessTime());
        pay.setUpdateTime(DateUtil.parse(DateUtil.now()));
        payService.updateByPayId(payId, pay);
    }
}
