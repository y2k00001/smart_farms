package com.neo.farmlands.controller.api;

import com.neo.common.annotation.Log;
import com.neo.common.core.controller.BaseController;
import com.neo.common.core.domain.AjaxResult;
import com.neo.common.core.domain.R;
import com.neo.common.core.page.TableDataInfo;
import com.neo.common.enums.BusinessType;
import com.neo.common.utils.poi.ExcelUtil;
import com.neo.farmlands.domain.entity.News;
import com.neo.farmlands.domain.vo.NewsVO;
import com.neo.farmlands.domain.vo.form.NewsForm;
import com.neo.farmlands.service.INewsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 资讯Controller
 *
 * @author neo
 * @date 2024-06-13
 */
@RestController
@RequestMapping("/h5/news")
@Api(tags = "资讯信息")
public class H5NewsController extends BaseController
{
    @Autowired
    private INewsService newsService;

    /**
     * 查询资讯列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询资讯列表")
    public TableDataInfo<NewsVO> list(NewsForm news)
    {
        startPage();
        List<NewsVO> list = newsService.selectNewsList(news);
        return getDataTable(list);
    }



    /**
     * 获取资讯详细信息
     */
    @GetMapping(value = "/{id}")
    @ApiOperation(value = "获取资讯信息详细信息")
    public R<NewsVO> getInfo(@PathVariable("id") String id)
    {
        return R.ok(newsService.selectNewsById(id));
    }

}
