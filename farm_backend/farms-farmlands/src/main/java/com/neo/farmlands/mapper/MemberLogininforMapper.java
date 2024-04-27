package com.neo.farmlands.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neo.farmlands.domain.entity.MemberLogininfor;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 会员登录记录Mapper接口
 *
 * @author zcc
 */
public interface MemberLogininforMapper extends BaseMapper<MemberLogininfor> {
    /**
     * 查询会员登录记录列表
     *
     * @param memberLogininfor 会员登录记录
     * @return 会员登录记录集合
     */
    List<MemberLogininfor> selectByEntity(MemberLogininfor memberLogininfor);

    int statLoginMember(@Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime);
}
