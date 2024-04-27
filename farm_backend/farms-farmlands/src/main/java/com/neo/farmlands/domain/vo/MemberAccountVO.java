package com.neo.farmlands.domain.vo;


import com.neo.common.annotation.Excel;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
/**
 * 会员账户表 数据视图对象
 *
 * @author zcc
 */
@Data
public class MemberAccountVO  {
   /** MEMBER_ID */
    private Long memberId;
   /** 积分余额 */
    @Excel(name = "积分余额")
    private BigDecimal integralBalance;
   /** 历史总共积分 */
    @Excel(name = "历史总共积分")
    private BigDecimal totalIntegralBalance;
   /** 修改时间 */
    private LocalDateTime updateTime;
   /** 创建时间 */
    private LocalDateTime createTime;
}
