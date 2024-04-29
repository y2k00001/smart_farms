package com.neo.farmlands.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.neo.farmlands.domain.entity.Farmland;
import com.neo.farmlands.domain.vo.FarmlandVO;
import com.neo.farmlands.mapper.FarmlandMapper;

/**
 * 农田信息Service接口
 *
 * @author neo
 * @date 2024-04-10
 */
public interface IFarmlandService extends IService<Farmland>
{
    /**
     * 查询农田信息
     *
     * @param id 农田信息主键
     * @return 农田信息
     */
    public Farmland selectFarmlandById(String id);

    public Farmland selectFarmlandByFarmlandId(String farmlandId);

    /**
     * 查询农田信息列表
     *
     * @param farmland 农田信息
     * @return 农田信息集合
     */
    public List<FarmlandVO> selectFarmlandList(Farmland farmland);



    /**
     * 新增农田信息
     *
     * @param farmland 农田信息
     * @return 结果
     */
    public int insertFarmland(Farmland farmland);

    /**
     * 修改农田信息
     *
     * @param farmland 农田信息
     * @return 结果
     */
    public int updateFarmland(Farmland farmland);

    /**
     * 批量删除农田信息
     *
     * @param ids 需要删除的农田信息主键集合
     * @return 结果
     */
    public int deleteFarmlandByIds(String[] ids);

    /**
     * 删除农田信息信息
     *
     * @param id 农田信息主键
     * @return 结果
     */
    public int deleteFarmlandById(String id);

    /**
     * description
     * @author monkey
     * @datetime  2024/4/19 14:06
     * @param farmlandId
     * @param isThrowException 不存在时是否抛异常中断
     * @return
     **/
    Farmland getOneByFarmlandId(String farmlandId, Boolean isThrowException);
}
