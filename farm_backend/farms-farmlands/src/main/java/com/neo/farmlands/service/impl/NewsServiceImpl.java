package com.neo.farmlands.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neo.common.utils.DateUtils;
import com.neo.farmlands.domain.entity.News;
import com.neo.farmlands.domain.vo.NewsVO;
import com.neo.farmlands.domain.vo.form.NewsForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.neo.farmlands.mapper.NewsMapper;

import com.neo.farmlands.service.INewsService;

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
        return newsMapper.selectNewsList(news);
    }

    /**
     * 新增资讯
     *
     * @param news 资讯
     * @return 结果
     */
    @Override
    public int insertNews(News news)
    {
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
