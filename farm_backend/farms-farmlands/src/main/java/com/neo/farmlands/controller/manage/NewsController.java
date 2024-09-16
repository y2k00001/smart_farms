package com.neo.farmlands.controller.manage;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.neo.common.core.domain.R;
import com.neo.farmlands.domain.entity.News;
import com.neo.farmlands.domain.vo.NewsVO;
import com.neo.farmlands.domain.vo.form.NewsForm;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.neo.common.annotation.Log;
import com.neo.common.core.controller.BaseController;
import com.neo.common.core.domain.AjaxResult;
import com.neo.common.enums.BusinessType;

import com.neo.farmlands.service.INewsService;
import com.neo.common.utils.poi.ExcelUtil;
import com.neo.common.core.page.TableDataInfo;

/**
 * 资讯Controller
 *
 * @author neo
 * @date 2024-06-13
 */
@RestController
@RequestMapping("/farmlands/news")
public class NewsController extends BaseController
{
    @Autowired
    private INewsService newsService;

    /**
     * 查询资讯列表
     */
    @PreAuthorize("@ss.hasPermi('farmlands:news:list')")
    @GetMapping("/list")
    public TableDataInfo<NewsVO> list(NewsForm news)
    {
        startPage();
        List<NewsVO> list = newsService.selectNewsList(news);
        return getDataTable(list);
    }

    /**
     * 导出资讯列表
     */
    @PreAuthorize("@ss.hasPermi('farmlands:news:export')")
    @Log(title = "资讯", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, NewsForm news)
    {
        List<NewsVO> list = newsService.selectNewsList(news);
        ExcelUtil<NewsVO> util = new ExcelUtil<NewsVO>(NewsVO.class);
        util.exportExcel(response, list, "资讯数据");
    }

    /**
     * 获取资讯详细信息
     */
    @PreAuthorize("@ss.hasPermi('farmlands:news:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(newsService.selectNewsById(id));
    }

    /**
     * 新增资讯
     */
    @PreAuthorize("@ss.hasPermi('farmlands:news:add')")
    @Log(title = "资讯", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public R add(@RequestBody NewsForm newsForm)
    {
        newsService.insertNews(newsForm);
        return R.ok();
    }

    /**
     * 修改资讯
     */
    @PreAuthorize("@ss.hasPermi('farmlands:news:edit')")
    @Log(title = "资讯", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody News news)
    {
        return toAjax(newsService.updateNews(news));
    }

    /**
     * 删除资讯
     */
    @PreAuthorize("@ss.hasPermi('farmlands:news:remove')")
    @Log(title = "资讯", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(newsService.deleteNewsByIds(ids));
    }
}
