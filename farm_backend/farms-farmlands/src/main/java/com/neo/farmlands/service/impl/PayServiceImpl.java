package com.neo.farmlands.service.impl;
import java.math.BigDecimal;
import java.util.Date;

import java.util.List;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neo.common.exception.ServiceException;
import com.neo.common.utils.DateUtils;
import com.neo.common.utils.uuid.IdUtils;
import com.neo.farmlands.constant.BusinessConstant;
import com.neo.farmlands.constant.IDConstants;
import com.neo.farmlands.domain.entity.FarmlandLessee;
import com.neo.farmlands.domain.entity.FarmlandLesseeOrder;
import com.neo.farmlands.domain.vo.PayFarmlandLesseeReqVO;
import com.neo.farmlands.domain.vo.UnifiedPayRS;
import com.neo.farmlands.enums.PayChannelEnum;
import com.neo.farmlands.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.neo.farmlands.mapper.PayMapper;
import com.neo.farmlands.domain.entity.Pay;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import static com.neo.farmlands.enums.PayChannelEnum.PAY_CHANNEL_WXPAY;

/**
 * 支付记录Service业务层处理
 *
 * @author neo
 * @date 2024-04-20
 */
@Service
public class PayServiceImpl extends ServiceImpl<PayMapper, Pay> implements IPayService
{
    @Resource
    private PayMapper payMapper;

    @Resource
    private IFarmlandLesseeService farmlandLesseeService;

    @Resource
    private IFarmlandLesseeOrderService farmlandLesseeOrderService;;

    @Resource
    private IOrderPayService orderPayService;

    @Resource
    private IPayService payService;

    @Resource
    private IThirdPaymentService thirdPaymentService;

    /**
     * 查询支付记录
     *
     * @param id 支付记录主键
     * @return 支付记录
     */
    @Override
    public Pay selectPayById(String id)
    {
        return payMapper.selectPayById(id);
    }

    /**
     * 查询支付记录列表
     *
     * @param pay 支付记录
     * @return 支付记录
     */
    @Override
    public List<Pay> selectPayList(Pay pay)
    {
        return payMapper.selectPayList(pay);
    }

    /**
     * 新增支付记录
     *
     * @param pay 支付记录
     * @return 结果
     */
    @Override
    public int insertPay(Pay pay)
    {
        pay.setCreateTime(DateUtils.getNowDate());
        return payMapper.insertPay(pay);
    }

    /**
     * 修改支付记录
     *
     * @param pay 支付记录
     * @return 结果
     */
    @Override
    public int updatePay(Pay pay)
    {
        pay.setUpdateTime(DateUtils.getNowDate());
        return payMapper.updatePay(pay);
    }

    /**
     * 批量删除支付记录
     *
     * @param ids 需要删除的支付记录主键
     * @return 结果
     */
    @Override
    public int deletePayByIds(String[] ids)
    {
        return payMapper.deletePayByIds(ids);
    }

    /**
     * 删除支付记录信息
     *
     * @param id 支付记录主键
     * @return 结果
     */
    @Override
    public int deletePayById(String id)
    {
        return payMapper.deletePayById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UnifiedPayRS prePayFarmlandLessee(PayFarmlandLesseeReqVO payFarmlandLesseeReqVO) {
        // 1.校验租赁合同是否存在
        FarmlandLessee farmlandLessee = farmlandLesseeService.getOneByFarmlandId(payFarmlandLesseeReqVO.getFarmlandLesseeId(),true);
        // 创建业务订单，业务订单关联支付单记录，支付单
        Pay pay = payService.saveFarmlandLesseePay(payFarmlandLesseeReqVO,farmlandLessee);
        FarmlandLesseeOrder farmlandLesseeOrder = farmlandLesseeOrderService.saveBizOrder(payFarmlandLesseeReqVO,pay);

        // 2.根据支付渠道，支付来源获取支付参数

        UnifiedPayRS unifiedPayRS = new UnifiedPayRS();
        switch (PayChannelEnum.getByValue(payFarmlandLesseeReqVO.getPayChannel())){
            case PAY_CHANNEL_WXPAY:
                unifiedPayRS = thirdPaymentService.wxPay(payFarmlandLesseeReqVO,pay,farmlandLesseeOrder);
                break;
            case PAY_CHANNEL_ALIPAY:
                // 1.2 支付宝支付
                unifiedPayRS = thirdPaymentService.aliPay(payFarmlandLesseeReqVO);
                break;
            case PAY_CHANNEL_CASH:
                // 1.3 线下支付
                throw new ServiceException("暂不支持线下支付渠道");
            default:
                throw new ServiceException(StrUtil.format("该支付渠道未接入【{}】！", payFarmlandLesseeReqVO.getPayChannel()));
        }

        // 3.调取支付接口获取拉起收银台的参数
        // 4.封装返回参数
        return unifiedPayRS;
    }

    @Override
    public Pay saveFarmlandLesseePay(PayFarmlandLesseeReqVO payFarmlandLesseeReqVO,FarmlandLessee farmlandLessee) {
        Pay pay = new Pay();
        pay.setId(IdUtil.fastSimpleUUID());
        pay.setPayId(IDConstants.FARMLAND_LESSEE_PAY_ID_PREFIX+ IdUtils.fastSimpleUUID());
        pay.setSubject("租赁合同下单");
        pay.setBody(payFarmlandLesseeReqVO.getPaySource().toString()+payFarmlandLesseeReqVO.getPayChannel()+"租赁合同下单");
        pay.setPaySource(payFarmlandLesseeReqVO.getPaySource());
        pay.setPayChannel(payFarmlandLesseeReqVO.getPayChannel());
        pay.setPayAmount(farmlandLessee.getLesseeAmount());
        pay.setExpiredTime(DateUtil.offset(new Date(), DateField.HOUR,2));
        pay.setCreateByName("");
        pay.setCreateBy("");

        this.save(pay);
        return pay;
    }
}
