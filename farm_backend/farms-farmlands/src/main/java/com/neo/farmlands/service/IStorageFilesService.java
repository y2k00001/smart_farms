package  com.neo.farmlands.service;

import com.neo.farmlands.domain.entity.StorageFiles;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author monkey
* @description 针对表【t_storage_files(存储资源文件)】的数据库操作Service
* @createDate 2024-04-28 23:46:02
*/
public interface IStorageFilesService extends IService<StorageFiles> {

    List<StorageFiles> listByFileIds(String[] fileIds);


    StorageFiles getOneByFileId(String fileId, Boolean isThrowException);
}
