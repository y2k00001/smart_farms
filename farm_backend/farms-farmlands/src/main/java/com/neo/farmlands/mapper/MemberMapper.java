package com.neo.farmlands.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neo.farmlands.domain.entity.Member;

import java.util.List;

/**
 * 会员信息Mapper接口
 *
 * @author zcc
 */
public interface MemberMapper extends BaseMapper<Member> {
    /**
     * 查询会员信息列表
     *
     * @param member 会员信息
     * @return 会员信息集合
     */
    List<Member> selectByEntity(Member member);
}
