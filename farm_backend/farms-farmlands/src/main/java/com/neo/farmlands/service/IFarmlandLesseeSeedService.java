package com.neo.farmlands.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.neo.farmlands.domain.entity.FarmlandLesseeSeed;
import com.neo.farmlands.domain.entity.Seed;

import java.util.List;

/**
* @author monkey
* @description 针对表【t_farmland_lessee_seed(租赁信息和种子关联表)】的数据库操作Service
* @createDate 2024-05-22 14:51:59
*/
public interface IFarmlandLesseeSeedService extends IService<FarmlandLesseeSeed> {

    List<Seed> getSeedListByFarmlandLesseeId(String farmlandLesseeId);
}
