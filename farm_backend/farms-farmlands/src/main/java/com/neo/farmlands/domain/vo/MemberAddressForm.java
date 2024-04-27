package com.neo.farmlands.domain.vo;

import lombok.Data;

@Data
public class MemberAddressForm {
    private Long id;
    private Long memberId;
    private String name;
    private String phone;
    private Integer defaultStatus;
    private String postCode;
    private String province;
    private String city;
    private String district;
    private String detailAddress;
    private Integer isDefault;
}
