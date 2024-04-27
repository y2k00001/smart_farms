package com.neo.farmlands.controller.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.neo.common.core.domain.R;
import com.neo.farmlands.domain.vo.WechatLoginForm;
import com.neo.farmlands.service.impl.MemberWechatService;
import com.neo.farmlands.utils.WechatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * h5/微信
 */
@RestController
@RequestMapping("")
public class WechatController {
    @Autowired
    private MemberWechatService memberWechatService;
    /**
     * 微信公众号服务器认证
     * @return
     */
    @GetMapping("/no-auth/wechat-server-auth")
    public String getHomeConfig(HttpServletRequest request) {
        String signature = request.getParameter("signature");
        String nonce = request.getParameter("nonce");
        String timestamp = request.getParameter("timestamp");
        String echostr = request.getParameter("echostr");
        if (WechatUtil.validParam(signature, "BLU9Jo7vo", timestamp, nonce)) {
            return echostr;
        }
        return "err";
    }
    /**
     * 微信公众号服务器认证
     * @return
     */
    @PostMapping("/no-auth/wechat/h5-login")
    public ResponseEntity<String> h5Login(@RequestBody WechatLoginForm form) {
        String token = memberWechatService.login(form);
        return ResponseEntity.ok("{\"data\": \"" + token + "\"}");
    }


    @GetMapping("/no-auth/wechat/getSessionId")
    public R<String> getSessionId(String code) {
        JSONObject object = memberWechatService.getSessionId(code);
        if (object != null) {
            String openId = object.getString("openid");
            String sessionId = object.getString("session_key");
            Map map = new HashMap();
            map.put("openId", openId);
            map.put("sessionId", sessionId);
            return R.ok(Base64Utils.encodeToString(JSON.toJSONString(map).getBytes()));
        }
        return R.ok(null);
    }

    @GetMapping("/no-auth/wechat/getSessionId2")
    public R<Map> getSessionId2(String code) {
        JSONObject object = memberWechatService.getSessionId(code);
        if (object != null) {
            String openId = object.getString("openid");
            String sessionId = object.getString("session_key");
            JSONObject obj = new JSONObject();
            obj.put("openId", openId);
            obj.put("sessionId", sessionId);
            Map map = new HashMap();
            map.put("data", Base64Utils.encodeToString(JSON.toJSONString(obj).getBytes()));
            map.put("token", memberWechatService.getToken(openId));
            return R.ok(map);
        }
        return R.ok(null);
    }
}
