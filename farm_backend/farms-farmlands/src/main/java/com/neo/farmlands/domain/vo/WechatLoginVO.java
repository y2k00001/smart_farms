package com.neo.farmlands.domain.vo;

import lombok.Data;

@Data
public class WechatLoginVO {

    private String data;
    private String key;
    private String sessionKey;
    private String openId;
}
