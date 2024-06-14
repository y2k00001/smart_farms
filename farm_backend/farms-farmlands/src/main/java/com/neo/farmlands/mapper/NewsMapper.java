package com.neo.farmlands.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neo.farmlands.domain.entity.News;
import com.neo.farmlands.domain.vo.NewsVO;
import com.neo.farmlands.domain.vo.form.NewsForm;

import java.util.List;


/**
 * 资讯Mapper接口
 *
 * @author neo
 * @date 2024-06-13
 */
public interface NewsMapper  extends BaseMapper<News>
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
     * @param news 资讯
     * @return 结果
     */
    public int insertNews(News news);

    /**
     * 修改资讯
     *
     * @param news 资讯
     * @return 结果
     */
    public int updateNews(News news);

    /**
     * 删除资讯
     *
     * @param id 资讯主键
     * @return 结果
     */
    public int deleteNewsById(String id);

    /**
     * 批量删除资讯
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteNewsByIds(String[] ids);
}
