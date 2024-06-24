package com.neo.farmlands.service;

import com.neo.farmlands.domain.entity.FarmlandArea;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author monkey
* @description 针对表【t_farmland_area(农田、租赁面积关联表)】的数据库操作Service
* @createDate 2024-06-23 23:59:31
*/
public interface IFarmlandAreaService extends IService<FarmlandArea> {

    FarmlandArea getOneByFarmlandLesseeId(String farmlandId);
}
