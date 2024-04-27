package com.neo.farmlands.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neo.farmlands.domain.entity.MemberWechat;


import java.util.List;

/**
 * 用户微信信息Mapper接口
 *
 * @author zcc
 */
public interface MemberWechatMapper extends BaseMapper<MemberWechat> {
    /**
     * 查询用户微信信息列表
     *
     * @param memberWechat 用户微信信息
     * @return 用户微信信息集合
     */
    List<MemberWechat> selectByEntity(MemberWechat memberWechat);
}
