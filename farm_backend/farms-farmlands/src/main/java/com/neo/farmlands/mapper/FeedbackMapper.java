package com.neo.farmlands.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neo.farmlands.domain.entity.Feedback;


import java.util.List;

/**
 * 意见反馈Mapper接口
 *
 * @author zcc
 */
public interface FeedbackMapper extends BaseMapper<Feedback> {
    /**
     * 查询意见反馈列表
     *
     * @param feedback 意见反馈
     * @return 意见反馈集合
     */
    List<Feedback> selectByEntity(Feedback feedback);
}
