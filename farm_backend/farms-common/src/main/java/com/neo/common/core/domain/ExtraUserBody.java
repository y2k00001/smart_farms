package com.neo.common.core.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExtraUserBody {
    private String login;
    private String nickname;
    private Integer sex;
    private String avatar;
    private String phone;
}
