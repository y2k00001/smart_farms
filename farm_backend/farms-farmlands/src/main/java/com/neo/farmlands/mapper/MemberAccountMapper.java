package com.neo.farmlands.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neo.farmlands.domain.entity.MemberAccount;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

/**
 * 会员账户表Mapper接口
 *
 * @author zcc
 */
public interface MemberAccountMapper extends BaseMapper<MemberAccount> {
    /**
     * 查询会员账户表列表
     *
     * @param memberAccount 会员账户表
     * @return 会员账户表集合
     */
    List<MemberAccount> selectByEntity(MemberAccount memberAccount);

    int updateIntegralBalance(@Param("amount") BigDecimal amount, @Param("memberId") Long memberId);

    int updateIntegral(@Param("useIntegral") BigDecimal useIntegral, @Param("memberId") Long memberId);
}
