package com.neo.farmlands.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neo.farmlands.domain.entity.FarmlandLesseeSeed;
import com.neo.farmlands.domain.entity.Seed;
import com.neo.farmlands.domain.entity.StorageFiles;
import com.neo.farmlands.domain.vo.SeedVO;
import com.neo.farmlands.service.IFarmlandLesseeSeedService;
import com.neo.farmlands.mapper.FarmlandLesseeSeedMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author monkey
* @description 针对表【t_farmland_lessee_seed(租赁信息和种子关联表)】的数据库操作Service实现
* @createDate 2024-05-22 14:51:59
*/
@Service
public class FarmlandLesseeSeedServiceImpl extends ServiceImpl<FarmlandLesseeSeedMapper, FarmlandLesseeSeed>
    implements IFarmlandLesseeSeedService {

    @Resource
    private FarmlandLesseeSeedMapper farmlandLesseeSeedMapper;

    @Resource
    private StorageFilesServiceImpl storageFilesService;

    @Override
    public List<Seed> getSeedListByFarmlandLesseeId(String farmlandLesseeId) {
        List<Seed> seedList =  farmlandLesseeSeedMapper.getSeedListByFarmlandLesseeId(farmlandLesseeId);
        if (seedList.size()>0){
            seedList.forEach(seed->{
                if(StrUtil.isNotBlank(seed.getFileIds())){
                    String[] fileIds = seed.getFileIds().split(",");
                    List<StorageFiles> storageFiles = storageFilesService.listByFileIds(fileIds);
                    seed.setFiles(storageFiles);
                }
            });

        }
        return seedList;
    }
}




