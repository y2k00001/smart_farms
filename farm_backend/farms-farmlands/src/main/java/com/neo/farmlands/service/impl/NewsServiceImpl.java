package com.neo.farmlands.service.impl;

import java.util.List;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neo.common.utils.DateUtils;
import com.neo.common.utils.DictUtils;
import com.neo.common.utils.IDGenerator;
import com.neo.common.utils.SecurityUtils;
import com.neo.common.utils.uuid.IdUtils;
import com.neo.farmlands.constant.BusinessConstant;
import com.neo.farmlands.constant.IDConstants;
import com.neo.farmlands.domain.entity.News;
import com.neo.farmlands.domain.entity.StorageFiles;
import com.neo.farmlands.domain.vo.NewsVO;
import com.neo.farmlands.domain.vo.form.NewsForm;
import com.neo.farmlands.service.IFarmlandService;
import com.neo.farmlands.service.IStorageFilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.neo.farmlands.mapper.NewsMapper;

import com.neo.farmlands.service.INewsService;

import javax.annotation.Resource;

/**
 * 资讯Service业务层处理
 *
 * @author neo
 * @date 2024-06-13
 */
@Service
public class NewsServiceImpl extends ServiceImpl<NewsMapper, News> implements INewsService
{
    @Autowired
    private NewsMapper newsMapper;

    @Resource
    private IStorageFilesService storageFilesService;

    @Resource
    private IFarmlandService farmlandService;

    /**
     * 查询资讯
     *
     * @param id 资讯主键
     * @return 资讯
     */
    @Override
    public NewsVO selectNewsById(String id)
    {
        return newsMapper.selectNewsById(id);
    }

    /**
     * 查询资讯列表
     *
     * @param news 资讯
     * @return 资讯
     */
    @Override
    public List<NewsVO> selectNewsList(NewsForm news)
    {
        news.setIsDeleted(0);
        List<NewsVO> newsVOS =  newsMapper.selectNewsList(news);
        if(newsVOS.size() > 0 ){
            newsVOS.forEach(newsVO -> {
                if(StrUtil.isNotBlank( newsVO.getPictureIds())){
                    List<StorageFiles> storageFiles = farmlandService.getStorageFiles(newsVO.getPictureIds());
                    newsVO.setPictureList(storageFiles);
                }
                newsVO.setThumbnailFileEntity(storageFilesService.getOneByFileId(newsVO.getThumbnailFile(),false));
                String newsTypeStr = DictUtils.getDictLabel(BusinessConstant.NEWS_TYPE, newsVO.getNewsType()+"");
                newsVO.setNewsTypeStr(newsTypeStr);
            });
        }
        return newsVOS;
    }

    /**
     * 新增资讯
     *
     * @param newsForm 资讯
     * @return 结果
     */
    @Override
    public int insertNews(NewsForm newsForm)
    {
        News news = new News();
        BeanUtil.copyProperties(newsForm,news);
        news.setId(String.valueOf(IdUtils.getSnowflakeId()));
        news.setNewsId(IDConstants.NEWS_ID_PREFIX+ IDGenerator.generateId());
        news.setCreateByName(SecurityUtils.getUsername());
        news.setCreateBy(SecurityUtils.getUserId().toString());
        news.setCreateTime(DateUtils.getNowDate());

        return newsMapper.insertNews(news);
    }

    /**
     * 修改资讯
     *
     * @param news 资讯
     * @return 结果
     */
    @Override
    public int updateNews(News news)
    {
        news.setUpdateTime(DateUtils.getNowDate());
        return newsMapper.updateNews(news);
    }

    /**
     * 批量删除资讯
     *
     * @param ids 需要删除的资讯主键
     * @return 结果
     */
    @Override
    public int deleteNewsByIds(String[] ids)
    {
        return newsMapper.deleteNewsByIds(ids);
    }

    /**
     * 删除资讯信息
     *
     * @param id 资讯主键
     * @return 结果
     */
    @Override
    public int deleteNewsById(String id)
    {
        return newsMapper.deleteNewsById(id);
    }
}
