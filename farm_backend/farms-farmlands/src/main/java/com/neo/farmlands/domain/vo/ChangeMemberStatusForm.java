package com.neo.farmlands.domain.vo;

import lombok.Data;

@Data
public class ChangeMemberStatusForm {
    private Long memberId;
    private Integer status;
}
