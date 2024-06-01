package com.neo.farmlands.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neo.farmlands.domain.entity.FarmlandLesseeSeed;
import com.neo.farmlands.domain.entity.Seed;

import java.util.List;

/**
* @author monkey
* @description 针对表【t_farmland_lessee_seed(租赁信息和种子关联表)】的数据库操作Mapper
* @createDate 2024-05-22 14:51:59
* @Entity com.neo.farmlands.domain.FarmlandLesseeSeed
*/
public interface FarmlandLesseeSeedMapper extends BaseMapper<FarmlandLesseeSeed> {

    List<Seed> getSeedListByFarmlandLesseeId(String farmlandLesseeId);
}




