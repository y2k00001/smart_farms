package com.neo.farmlands.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neo.farmlands.domain.entity.FarmlandArea;
import com.neo.farmlands.service.IFarmlandAreaService;
import com.neo.farmlands.mapper.FarmlandAreaMapper;
import org.springframework.stereotype.Service;

/**
* @author monkey
* @description 针对表【t_farmland_area(农田、租赁面积关联表)】的数据库操作Service实现
* @createDate 2024-06-23 23:59:31
*/
@Service
public class FarmlandAreaServiceImpl extends ServiceImpl<FarmlandAreaMapper, FarmlandArea>
    implements IFarmlandAreaService {

    @Override
    public FarmlandArea getOneByFarmlandLesseeId(String farmlandId) {
        LambdaQueryWrapper<FarmlandArea> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(FarmlandArea::getFarmlandId, farmlandId).eq(FarmlandArea::getIsDeleted, 0);
        return this.getOne(queryWrapper);
    }
}




