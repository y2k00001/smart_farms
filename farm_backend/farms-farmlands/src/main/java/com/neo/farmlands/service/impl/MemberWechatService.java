package com.neo.farmlands.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.github.pagehelper.PageHelper;
import com.neo.common.core.domain.ExtraUserBody;
import com.neo.common.core.domain.entity.SysUser;
import com.neo.farmlands.domain.WechatPayData;
import com.neo.farmlands.domain.entity.Member;
import com.neo.farmlands.domain.entity.MemberWechat;
import com.neo.farmlands.domain.vo.MemberWechatQuery;
import com.neo.farmlands.domain.vo.WechatLoginForm;
import com.neo.farmlands.mapper.MemberWechatMapper;

import com.neo.farmlands.utils.AccessTokenResp;
import com.neo.farmlands.utils.UserInfoResp;
import com.neo.farmlands.utils.WechatUtil;
import com.neo.framework.web.service.SysLoginService;
import com.neo.system.mapper.SysUserMapper;
import com.neo.system.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

/**
 * 用户微信信息Service业务层处理
 *
 *
 * @author zcc
 */
@Slf4j
@Service
public class MemberWechatService {
    @Resource
    private MemberWechatMapper memberWechatMapper;

    @Resource
    private SysUserMapper sysUserMapper;
    @Resource
    private ISysUserService userService;
    @Resource
    private SysLoginService loginService;
    @Resource
    private RestTemplate restTemplate;
    @Autowired
    private H5MemberService memberService;
    private static String LOGIN_URL = "https://api.weixin.qq.com/sns/jscode2session?appid=#{APPID}&secret=#{SECRET}&js_code=#{JSCODE}&grant_type=authorization_code";


    /**
     * 查询用户微信信息
     *
     * @param id 用户微信信息主键
     * @return 用户微信信息
     */
    public MemberWechat selectById(Long id) {
        return memberWechatMapper.selectById(id);
    }

    /**
     * 查询用户微信信息列表
     *
     * @param query 查询条件
     * @param page 分页条件
     * @return 用户微信信息
     */
    public List<MemberWechat> selectList(MemberWechatQuery query, Pageable page) {
        if (page != null) {
            PageHelper.startPage(page.getPageNumber() + 1, page.getPageSize());
        }
        QueryWrapper<MemberWechat> qw = new QueryWrapper<>();
        Long memberId = query.getMemberId();
        if (memberId != null) {
            qw.eq("member_id", memberId);
        }
        String unionid = query.getUnionid();
        if (!StringUtils.isEmpty(unionid)) {
            qw.eq("unionid", unionid);
        }
        String openid = query.getOpenid();
        if (!StringUtils.isEmpty(openid)) {
            qw.eq("openid", openid);
        }
        String routineOpenid = query.getRoutineOpenid();
        if (!StringUtils.isEmpty(routineOpenid)) {
            qw.eq("routine_openid", routineOpenid);
        }
        Integer groupid = query.getGroupid();
        if (groupid != null) {
            qw.eq("groupid", groupid);
        }
        String tagidList = query.getTagidList();
        if (!StringUtils.isEmpty(tagidList)) {
            qw.eq("tagid_list", tagidList);
        }
        Integer subscribe = query.getSubscribe();
        if (subscribe != null) {
            qw.eq("subscribe", subscribe);
        }
        Integer subscribeTime = query.getSubscribeTime();
        if (subscribeTime != null) {
            qw.eq("subscribe_time", subscribeTime);
        }
        String sessionKey = query.getSessionKey();
        if (!StringUtils.isEmpty(sessionKey)) {
            qw.eq("session_key", sessionKey);
        }
        String accessToken = query.getAccessToken();
        if (!StringUtils.isEmpty(accessToken)) {
            qw.eq("access_token", accessToken);
        }
        Integer expiresIn = query.getExpiresIn();
        if (expiresIn != null) {
            qw.eq("expires_in", expiresIn);
        }
        String refreshToken = query.getRefreshToken();
        if (!StringUtils.isEmpty(refreshToken)) {
            qw.eq("refresh_token", refreshToken);
        }
        LocalDateTime expireTime = query.getExpireTime();
        if (expireTime != null) {
            qw.eq("expire_time", expireTime);
        }
        return memberWechatMapper.selectList(qw);
    }

    /**
     * 新增用户微信信息
     *
     * @param memberWechat 用户微信信息
     * @return 结果
     */
    public int insert(MemberWechat memberWechat) {
        memberWechat.setCreateTime(new Date());
        return memberWechatMapper.insert(memberWechat);
    }

    /**
     * 修改用户微信信息
     *
     * @param memberWechat 用户微信信息
     * @return 结果
     */
    public int update(MemberWechat memberWechat) {
        return memberWechatMapper.updateById(memberWechat);
    }

    /**
     * 删除用户微信信息信息
     *
     * @param id 用户微信信息主键
     * @return 结果
     */
    public int deleteById(Long id) {
        return memberWechatMapper.deleteById(id);
    }

    public String login(WechatLoginForm form) {
        // 1. code -> token
        AccessTokenResp tokenResp = WechatUtil.getAccessToken(form.getCode());
        // 2. token -> user_info
        UserInfoResp info = null;
        try {
            info = WechatUtil.getUserInfo(tokenResp.getAccessToken(), tokenResp.getOpenid());
        } catch (Exception e) {
            log.error("form: {}", form.getCode(), e);
        }
        // 3. 查找用户是否存在, 若没有则创建
        LambdaQueryWrapper<MemberWechat> qw = new LambdaQueryWrapper<>();
        qw.eq(MemberWechat::getOpenid, tokenResp.getOpenid());
        MemberWechat m = memberWechatMapper.selectOne(qw);
        SysUser u;
        if (m != null) {
            SysUser update = new SysUser();
            if (info != null) {
                if (StrUtil.isNotEmpty(info.getNickname())) {
                    update.setNickName(info.getNickname());
                }
                if (info.getSex() != null) {
                    update.setSex(info.getSex() + "");
                }
                if (StrUtil.isNotEmpty(info.getHeadimgurl())) {
                    update.setAvatar(info.getHeadimgurl());
                }
                update.setUserId(m.getMemberId());
                sysUserMapper.updateUser(update);
            }
            u = sysUserMapper.selectUserById(m.getMemberId());
        } else {
            ExtraUserBody body = ExtraUserBody.builder()
                    .nickname(info == null ? "" : info.getNickname())
                    .avatar(info == null ? "" : info.getHeadimgurl())
                    .login(RandomUtil.randomNumbers(9))
                    .sex(info == null ? null : info.getSex())
                    .build();
            u = loginService.initVipUser(body);
            MemberWechat w = BeanUtil.copyProperties(tokenResp, MemberWechat.class);
            w.setMemberId(u.getUserId());
            w.setExpireTime(LocalDateTime.now().plus(tokenResp.getExpiresIn(), ChronoUnit.SECONDS));
            memberWechatMapper.insert(w);
        }
        // 4. 生成token
        return loginService.createToken(u);
    }

    public JSONObject getSessionId(String code) {
        String url = LOGIN_URL.replace("#{APPID}", WechatPayData.miniProgramAppId)
                .replace("#{SECRET}", WechatPayData.miniProgramSecret)
                .replace("#{JSCODE}", code);
        log.info("【调用微信接口】获取openid，url：{}", url);
        try {
            ResponseEntity<String> res = restTemplate.getForEntity(url, String.class);
            String body = res.getBody();
            log.info("【调用微信接口】获取openid，响应：{}", res);
            if (com.neo.common.utils.StringUtils.isEmpty(body)) {
                throw new Exception("获取openid出错");
            }
            return JSONObject.parseObject(body);
        } catch (Exception e) {
            log.error("获取openid报错", e);
            return null;
        }
    }

    public String getToken(String openId) {
        //判断openid是否存在
        QueryWrapper<MemberWechat> qw = new QueryWrapper<>();
        qw.eq("routine_openid", openId);
        MemberWechat memberWechat = memberWechatMapper.selectOne(qw);
        if (memberWechat == null) {
            return null;
        }
        Member member = memberService.selectById(memberWechat.getMemberId());
        return memberService.getLoginResponse(member.getId()).getToken();
    }
}
