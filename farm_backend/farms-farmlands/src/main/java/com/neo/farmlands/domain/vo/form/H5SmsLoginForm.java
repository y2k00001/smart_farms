package com.neo.farmlands.domain.vo.form;


import com.neo.farmlands.domain.vo.WechatUserAuth;
import lombok.Data;

@Data
public class H5SmsLoginForm extends H5LoginForm {
    /** 验证码 */
    private String code;
    /** uuid */
    private String uuid;
    /** 微信授权信息 */
    private WechatUserAuth authInfo;
    /** 小程序openid **/
    private String mpOpenId;
}
