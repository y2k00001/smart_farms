package com.neo.farmlands.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.neo.farmlands.domain.entity.StorageFiles;
import com.neo.farmlands.domain.vo.LesseeOrderVO;
import com.neo.farmlands.domain.vo.form.H5PreLesseeOrderForm;
import com.neo.farmlands.mapper.FarmlandMapper;
import com.neo.farmlands.mapper.MemberAddressMapper;
import com.neo.farmlands.service.IStorageFilesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
}
