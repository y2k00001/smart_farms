package com.neo.farmlands.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.neo.farmlands.domain.entity.FarmlandLessee;
import com.neo.farmlands.domain.vo.FarmlandLesseeReqVO;
import com.neo.farmlands.domain.vo.FarmlandLesseeVO;
import com.neo.farmlands.domain.vo.PayFarmlandLesseeReqVO;
import com.neo.farmlands.domain.vo.form.FarmlandLesseeForm;

/**
 * 农田租赁信息Service接口
 *
 * @author neo
 * @date 2024-04-15
 */
public interface IFarmlandLesseeService extends IService<FarmlandLessee>
{
    /**
     * 查询农田租赁信息
     *
     * @param id 农田租赁信息主键
     * @return 农田租赁信息
     */
    public FarmlandLessee selectFarmlandLesseeById(String id);

    /**
     * 查询农田租赁信息列表
     *
     * @param farmlandLessee 农田租赁信息
     * @return 农田租赁信息集合
     */
    public List<FarmlandLessee> selectFarmlandLesseeList(FarmlandLessee farmlandLessee);

    /**
     * 新增农田租赁信息
     *
     * @param farmlandLessee 农田租赁信息
     * @return 结果
     */
    public int insertFarmlandLessee(FarmlandLessee farmlandLessee);

    /**
     * 修改农田租赁信息
     *
     * @param farmlandLessee 农田租赁信息
     * @return 结果
     */
    public int updateFarmlandLessee(FarmlandLessee farmlandLessee);

    /**
     * 批量删除农田租赁信息
     *
     * @param ids 需要删除的农田租赁信息主键集合
     * @return 结果
     */
    public int deleteFarmlandLesseeByIds(String[] ids);

    /**
     * 删除农田租赁信息信息
     *
     * @param id 农田租赁信息主键
     * @return 结果
     */
    public int deleteFarmlandLesseeById(String id);

    /**
     * 提交农田租赁信息
     * @author monkey
     * @datetime  2024/4/19 11:45
     * @param farmlandLesseeReq
     * @return
     **/
    void submitFarmlandLessee(FarmlandLesseeReqVO farmlandLesseeReq);

    /**
     * description
     * @author monkey
     * @datetime  2024/4/21 11:29
     * @param farmlandLesseeId
     * @param isThrowException
     * @return {@link null}
     **/
    FarmlandLessee getOneByFarmlandId(String farmlandLesseeId, boolean isThrowException);

    List<FarmlandLesseeVO> myFarmlandLesseeList(FarmlandLesseeForm farmlandLesseeForm);

    FarmlandLesseeVO myFarmlandLesseeDetail(FarmlandLesseeForm farmlandLesseeForm);
}
