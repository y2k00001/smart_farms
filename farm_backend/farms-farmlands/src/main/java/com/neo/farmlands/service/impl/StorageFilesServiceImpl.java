package  com.neo.farmlands.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neo.farmlands.domain.entity.StorageFiles;
import com.neo.farmlands.service.IStorageFilesService;
import  com.neo.farmlands.mapper.StorageFilesMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author monkey
* @description 针对表【t_storage_files(存储资源文件)】的数据库操作Service实现
* @createDate 2024-04-28 23:46:02
*/
@Service
public class StorageFilesServiceImpl extends ServiceImpl<StorageFilesMapper, StorageFiles>
    implements IStorageFilesService {


    @Resource
    private StorageFilesMapper storageFilesMapper;
    @Override
    public List<StorageFiles> listByFileIds(String[] fileIds) {
        List<StorageFiles> storageFiles =  storageFilesMapper.listByFileIds(fileIds);
        return storageFiles;
    }

    @Override
    public StorageFiles getOneByFileId(String fileId, Boolean isThrowException) {
        LambdaQueryWrapper<StorageFiles> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StorageFiles::getFileId, fileId).eq(StorageFiles::getIsDeleted, 0);

        StorageFiles storageFiles =  this.getOne(queryWrapper);
        if(BeanUtil.isEmpty(storageFiles)){
            if(isThrowException){
                throw new RuntimeException(StrUtil.format("编号【】文件不存在",fileId));
            }else {
                return new StorageFiles();
            }
        }
        return storageFiles;
    }
}




