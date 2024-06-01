package com.neo.farmlands.service.impl;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.common.collect.Maps;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neo.common.exception.ServiceException;
import com.neo.common.utils.DateUtils;
import com.neo.common.utils.uuid.IdUtils;
import com.neo.farmlands.constant.IDConstants;
import com.neo.farmlands.domain.*;
import com.neo.farmlands.domain.entity.Farmland;
import com.neo.farmlands.domain.entity.FarmlandLessee;
import com.neo.farmlands.domain.entity.Lessee;
import com.neo.farmlands.domain.entity.StorageFiles;
import com.neo.farmlands.domain.vo.FarmlandLesseeReqVO;
import com.neo.farmlands.domain.vo.FarmlandLesseeVO;
import com.neo.farmlands.domain.vo.FarmlandVO;
import com.neo.farmlands.domain.vo.form.FarmlandLesseeForm;
import com.neo.farmlands.enums.FarmlandLesseeStatusEnum;
import com.neo.farmlands.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.neo.farmlands.mapper.FarmlandLesseeMapper;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 农田租赁信息Service业务层处理
 *
 * @author neo
 * @date 2024-04-15
 */
@Service
public class FarmlandLesseeServiceImpl extends ServiceImpl<FarmlandLesseeMapper, FarmlandLessee> implements IFarmlandLesseeService
{
    @Resource
    private FarmlandLesseeMapper farmlandLesseeMapper;


    @Resource
    private IFarmlandService farmlandService;

    @Resource
    private ILandAreaService landAreaService;
    @Resource
    private ISeedService seedService;

    @Resource
    private ILandServiceService landServiceService;

    @Resource
    private ILesseeService lesseeService;
    @Resource
    private IStorageFilesService storageFilesService;

    @Resource
    private IFarmlandLesseeOrderService farmlandLesseeOrderService;

    @Resource
    private IFarmlandLesseeSeedService farmlandLesseeSeedService;

    /**
     * 查询农田租赁信息
     *
     * @param id 农田租赁信息主键
     * @return 农田租赁信息
     */
    @Override
    public FarmlandLessee selectFarmlandLesseeById(String id)
    {
        return farmlandLesseeMapper.selectFarmlandLesseeById(id);
    }

    /**
     * 查询农田租赁信息列表
     *
     * @param farmlandLessee 农田租赁信息
     * @return 农田租赁信息
     */
    @Override
    public List<FarmlandLessee> selectFarmlandLesseeList(FarmlandLessee farmlandLessee)
    {
        return farmlandLesseeMapper.selectFarmlandLesseeList(farmlandLessee);
    }

    /**
     * 新增农田租赁信息
     *
     * @param farmlandLessee 农田租赁信息
     * @return 结果
     */
    @Override
    public int insertFarmlandLessee(FarmlandLessee farmlandLessee)
    {
        farmlandLessee.setCreateTime(DateUtils.getNowDate());
        return farmlandLesseeMapper.insertFarmlandLessee(farmlandLessee);
    }

    /**
     * 修改农田租赁信息
     *
     * @param farmlandLessee 农田租赁信息
     * @return 结果
     */
    @Override
    public int updateFarmlandLessee(FarmlandLessee farmlandLessee)
    {
        farmlandLessee.setUpdateTime(DateUtils.getNowDate());
        return farmlandLesseeMapper.updateFarmlandLessee(farmlandLessee);
    }

    /**
     * 批量删除农田租赁信息
     *
     * @param ids 需要删除的农田租赁信息主键
     * @return 结果
     */
    @Override
    public int deleteFarmlandLesseeByIds(String[] ids)
    {
        return farmlandLesseeMapper.deleteFarmlandLesseeByIds(ids);
    }

    /**
     * 删除农田租赁信息信息
     *
     * @param id 农田租赁信息主键
     * @return 结果
     */
    @Override
    public int deleteFarmlandLesseeById(String id)
    {
        return farmlandLesseeMapper.deleteFarmlandLesseeById(id);
    }

    /**
     * 提交农田租赁信息
     * @author monkey
     * @datetime  2024/4/19 11:48
     * @param farmlandLesseeReq
     * @return
     **/
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void submitFarmlandLessee(FarmlandLesseeReqVO farmlandLesseeReq) {
        // 1.校验数据
        validatePrams(farmlandLesseeReq);
        // 2.保存租户信息
        Lessee lessee = saveLesseeInfo(farmlandLesseeReq);

        //     3.获取相关信息，农田、面积、种子、服务、租户、当前用户
        Farmland farmland =   farmlandService.getOneByFarmlandId(farmlandLesseeReq.getFarmlandId(),true);
        //        4.保存租赁信息
        FarmlandLessee farmlandLessee = new FarmlandLessee();

        farmlandLessee.setFarmlandLesseeId(IDConstants.FARMLAND_LESSEE_ID_PREFIX+IdUtils.fastSimpleUUID());
        farmlandLessee.setFarmlandId(farmlandLesseeReq.getFarmlandId());
        farmlandLessee.setLesseeId(lessee.getLesseeId());
        farmlandLessee.setLesseePrice(farmland.getLeasePrice());
        farmlandLessee.setLesseeAmount(farmland.getLeasePrice());
        farmlandLessee.setLesseeDay(farmlandLesseeReq.getLesseeDay());
        farmlandLessee.setStatus(0L);
        farmlandLessee.setCreateTime(new Date());

        this.save(farmlandLessee);
    }

    @Override
    public FarmlandLessee getOneByFarmlandId(String farmlandLesseeId, boolean isThrowException) {
        LambdaQueryWrapper<FarmlandLessee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(FarmlandLessee::getFarmlandId, farmlandLesseeId).eq(FarmlandLessee::getIsDeleted, "0");
        FarmlandLessee farmlandLessee = this.getOne(queryWrapper);
        if(BeanUtil.isEmpty(farmlandLessee) ){
            if(isThrowException){
                throw new ServiceException(StrUtil.format("编号【】租赁合同不存在!",farmlandLesseeId));
            }else {
                return new FarmlandLessee();
            }
        }
        return farmlandLessee;
    }

    @Override
    public List<FarmlandLesseeVO> myFarmlandLesseeList(FarmlandLesseeForm farmlandLesseeForm) {

        if(BeanUtil.isEmpty(farmlandLesseeForm.getStatusList()) ||  !(farmlandLesseeForm.getStatusList().size() > 0) ){
            List<Long> statusList = new ArrayList<>();
            statusList.add(FarmlandLesseeStatusEnum.FARMLAND_LESSEE_STATUS_NO_PAY.getCode());
            statusList.add(FarmlandLesseeStatusEnum.FARMLAND_LESSEE_STATUS_PLANTING.getCode());
            statusList.add(FarmlandLesseeStatusEnum.FARMLAND_LESSEE_STATUS_FINISHED.getCode());
            farmlandLesseeForm.setStatusList(statusList);
        }

        List<FarmlandLesseeVO> farmlandLesseeVOList = farmlandLesseeMapper.getFarmlandLesseeListByEntity(farmlandLesseeForm);
        // TODO 先循环查询吧，固定的页码记录数，
        farmlandLesseeVOList.forEach(farmlandLesseeVO -> {
            FarmlandVO farmlandVO = BeanUtil.copyProperties( farmlandService.selectFarmlandByFarmlandId(farmlandLesseeVO.getFarmlandId()) ,FarmlandVO.class);
            if(StrUtil.isNotBlank(farmlandVO.getFileIds())){
                String[] fileIds = farmlandVO.getFileIds().split(",");
                List<StorageFiles> storageFiles = storageFilesService.listByFileIds(fileIds);
                farmlandVO.setFiles(storageFiles);
            }
            farmlandLesseeVO.setFarmlandVO(farmlandVO);
            farmlandLesseeVO.setLandArea(landAreaService.getOneByLandAreaId(farmlandLesseeVO.getLandAreaId(),false));
            farmlandLesseeVO.setLandService(landServiceService.getOneByServiceId(farmlandLesseeVO.getServiceId(),false));
            farmlandLesseeVO.setFarmlandLesseeOrder(farmlandLesseeOrderService.getOneByFarmlandLesseeId(farmlandLesseeVO.getFarmlandLesseeId(),false));
        });
        return farmlandLesseeVOList;
    }

    @Override
    public FarmlandLesseeVO myFarmlandLesseeDetail(FarmlandLesseeForm farmlandLesseeForm) {
        FarmlandLesseeVO farmlandLesseeVO= farmlandLesseeMapper.getFarmlandLesseeByEntity(farmlandLesseeForm);


        FarmlandVO farmlandVO = BeanUtil.copyProperties( farmlandService.selectFarmlandByFarmlandId(farmlandLesseeVO.getFarmlandId()) ,FarmlandVO.class);
        if(StrUtil.isNotBlank(farmlandVO.getFileIds())){
            String[] fileIds = farmlandVO.getFileIds().split(",");
            List<StorageFiles> storageFiles = storageFilesService.listByFileIds(fileIds);
            farmlandVO.setFiles(storageFiles);
        }
        farmlandLesseeVO.setFarmlandVO(farmlandVO);
        farmlandLesseeVO.setLandArea(landAreaService.getOneByLandAreaId(farmlandLesseeVO.getLandAreaId(),false));
        farmlandLesseeVO.setLandService(landServiceService.getOneByServiceId(farmlandLesseeVO.getServiceId(),false));
        farmlandLesseeVO.setFarmlandLesseeOrder(farmlandLesseeOrderService.getOneByFarmlandLesseeId(farmlandLesseeVO.getFarmlandLesseeId(),false));
        farmlandLesseeVO.setSeedList(farmlandLesseeSeedService.getSeedListByFarmlandLesseeId(farmlandLesseeForm.getFarmlandLesseeId()));
        farmlandLesseeVO.setLessee(lesseeService.getOneByLesseeId(farmlandLesseeVO.getLesseeId(),false));
        return farmlandLesseeVO;
    }

    private Lessee saveLesseeInfo(FarmlandLesseeReqVO farmlandLesseeReq) {
        Lessee lessee = new Lessee();
        lessee.setLesseeId(IDConstants.LESSEE_ID_PREFIX + IdUtils.fastSimpleUUID());
        lessee.setLesseeName(farmlandLesseeReq.getLesseeName());
        lessee.setLesseePhone(farmlandLesseeReq.getLesseePhone());
        lessee.setRemark(farmlandLesseeReq.getLesseeRemark());
        lesseeService.save(lessee);
        return lessee;
    }

    private void validatePrams(FarmlandLesseeReqVO farmlandLesseeReq) {
        farmlandService.getOneByFarmlandId(farmlandLesseeReq.getFarmlandId(),true);
        landAreaService.getOneByLandAreaId(farmlandLesseeReq.getLandAreaId(),true);
        seedService.getOneBySeedId(farmlandLesseeReq.getSeedId(),true);
        landServiceService.getOneByServiceId(farmlandLesseeReq.getServiceId(),true);


    }
}
