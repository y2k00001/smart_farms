package  com.neo.farmlands.mapper;

import com.neo.farmlands.domain.entity.StorageFiles;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author monkey
* @description 针对表【t_storage_files(存储资源文件)】的数据库操作Mapper
* @createDate 2024-04-28 23:46:02
* @Entity  com.neo.farmlands.domain.entity.StorageFiles
*/
public interface StorageFilesMapper extends BaseMapper<StorageFiles> {

    List<StorageFiles> listByFileIds(@Param("fileIds") String[] fileIds);
}




