package com.neo.farmlands.service.impl;

import com.neo.farmlands.domain.vo.LesseeOrderVO;
import com.neo.farmlands.domain.vo.form.H5PreLesseeOrderForm;
import com.neo.farmlands.mapper.FarmlandMapper;
import com.neo.farmlands.mapper.MemberAddressMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class H5OrderService {

    @Autowired
    private MemberAddressMapper memberAddressMapper;

    @Autowired
    private FarmlandMapper farmlandMapper;



    public void addOrderCheck() {

    }


    public LesseeOrderVO getPreOrder(H5PreLesseeOrderForm lesseeOrderForm) {
        LesseeOrderVO lesseeOrderVO = new LesseeOrderVO();
        lesseeOrderVO = farmlandMapper.getFarmlandPreOrder(lesseeOrderForm);
        return lesseeOrderVO;
    }
}
