package com.neo.farmlands.controller.manage;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.neo.common.core.domain.R;
import com.neo.farmlands.domain.vo.SeedVO;
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
import com.neo.farmlands.domain.entity.Seed;
import com.neo.farmlands.service.ISeedService;
import com.neo.common.utils.poi.ExcelUtil;
import com.neo.common.core.page.TableDataInfo;

/**
 * 种子信息Controller
 *
 * @author neo
 * @date 2024-04-16
 */
@RestController
@RequestMapping("/farmlands/seed")
public class SeedController extends BaseController
{
    @Autowired
    private ISeedService seedService;

    /**
     * 查询种子信息列表
     */
    @PreAuthorize("@ss.hasPermi('farmlands:seed:list')")
    @GetMapping("/list")
    public TableDataInfo list(Seed seed)
    {
        startPage();
        List<Seed> list = seedService.selectSeedList(seed);
        return getDataTable(list);
    }

    /**
     * 导出种子信息列表
     */
    @PreAuthorize("@ss.hasPermi('farmlands:seed:export')")
    @Log(title = "种子信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Seed seed)
    {
        List<Seed> list = seedService.selectSeedList(seed);
        ExcelUtil<Seed> util = new ExcelUtil<Seed>(Seed.class);
        util.exportExcel(response, list, "种子信息数据");
    }

    /**
     * 获取种子信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('farmlands:seed:query')")
    @GetMapping(value = "/{id}")
    public R<SeedVO> getInfo(@PathVariable("id") String id)
    {
        return R.ok(seedService.selectSeedById(id));
    }

    /**
     * 新增种子信息
     */
    @PreAuthorize("@ss.hasPermi('farmlands:seed:add')")
    @Log(title = "种子信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Seed seed)
    {
        return toAjax(seedService.insertSeed(seed));
    }

    /**
     * 修改种子信息
     */
    @PreAuthorize("@ss.hasPermi('farmlands:seed:edit')")
    @Log(title = "种子信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Seed seed)
    {
        return toAjax(seedService.updateSeed(seed));
    }

    /**
     * 删除种子信息
     */
    @PreAuthorize("@ss.hasPermi('farmlands:seed:remove')")
    @Log(title = "种子信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(seedService.deleteSeedByIds(ids));
    }
}
