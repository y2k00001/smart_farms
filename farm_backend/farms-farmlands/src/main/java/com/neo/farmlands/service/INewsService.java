package com.neo.farmlands.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.neo.farmlands.domain.entity.News;
import com.neo.farmlands.domain.vo.NewsVO;
import com.neo.farmlands.domain.vo.form.NewsForm;

import java.util.List;


/**
 * 资讯Service接口
 *
 * @author neo
 * @date 2024-06-13
 */
public interface INewsService extends IService<News>
{
    /**
     * 查询资讯
     *
     * @param id 资讯主键
     * @return 资讯
     */
    public NewsVO selectNewsById(String id);

    /**
     * 查询资讯列表
     *
     * @param news 资讯
     * @return 资讯集合
     */
    public List<NewsVO> selectNewsList(NewsForm news);

    /**
     * 新增资讯
     *
     * @param newsForm 资讯
     * @return 结果
     */
    public int insertNews(NewsForm newsForm);

    /**
     * 修改资讯
     *
     * @param news 资讯
     * @return 结果
     */
    public int updateNews(News news);

    /**
     * 批量删除资讯
     *
     * @param ids 需要删除的资讯主键集合
     * @return 结果
     */
    public int deleteNewsByIds(String[] ids);

    /**
     * 删除资讯信息
     *
     * @param id 资讯主键
     * @return 结果
     */
    public int deleteNewsById(String id);
}
