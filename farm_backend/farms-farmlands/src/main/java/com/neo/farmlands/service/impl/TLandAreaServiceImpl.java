package com.neo.farmlands.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neo.farmlands.domain.entity.TLandArea;
import com.neo.farmlands.service.ITLandAreaService;
import com.neo.farmlands.mapper.TLandAreaMapper;
import org.springframework.stereotype.Service;

/**
* @author monkey
* @description 针对表【t_land_area(农田租赁最小面积表)】的数据库操作Service实现
* @createDate 2024-04-30 00:11:43
*/
@Service
public class TLandAreaServiceImpl extends ServiceImpl<TLandAreaMapper, TLandArea>
    implements ITLandAreaService {

}




