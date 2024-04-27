package com.neo.farmlands.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;

import com.neo.common.core.redis.RedisCache;
import com.neo.common.utils.AesCryptoUtils;
import com.neo.common.utils.PhoneUtils;
import com.neo.common.utils.SecurityUtils;
import com.neo.common.utils.ServletUtils;
import com.neo.common.utils.ip.AddressUtils;
import com.neo.common.utils.ip.IpUtils;
import com.neo.farmlands.config.AESForWeixinGetPhoneNumber;
import com.neo.farmlands.constant.BusinessConstant;
import com.neo.common.core.domain.model.LoginMember;
import com.neo.farmlands.domain.entity.Member;
import com.neo.farmlands.domain.entity.MemberLogininfor;
import com.neo.farmlands.domain.entity.MemberWechat;
import com.neo.farmlands.domain.vo.*;
import com.neo.farmlands.domain.vo.form.BindOpenIDForm;
import com.neo.farmlands.domain.vo.form.H5AccountLoginForm;
import com.neo.farmlands.domain.vo.form.H5SmsLoginForm;
import com.neo.farmlands.mapper.MemberLogininforMapper;
import com.neo.farmlands.mapper.MemberMapper;
import com.neo.farmlands.mapper.MemberWechatMapper;
import com.neo.framework.config.LocalDataUtil;
import com.neo.framework.web.service.TokenService;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Date;

@Service
@Slf4j
public class H5MemberService {

    @Resource
    private MemberMapper memberMapper;

    @Resource
    private RedisCache redisCache;

    @Resource
    private TokenService tokenService;

    @Value("${aes.key}")
    private String aesKey;

    @Resource
    private WechatAuthService wechatAuthService;

    @Autowired
    private MemberWechatMapper memberWechatMapper;

    @Autowired
    private MemberLogininforService memberLogininforService;

    @Autowired
    private MemberLogininforMapper memberLogininforMapper;


    /**
     * 注册
     * @param request 注册请求体
     * @return 结果
     */
    @Transactional
    public RegisterVO register(RegisterForm request){

        RegisterVO response = new RegisterVO();
        //校验验证码
        this.validateVerifyCode(request.getUuid(), request.getMobile(), request.getCode());
        //创建会员
        Member member = new Member();
        member.setPhoneEncrypted(AesCryptoUtils.encrypt(aesKey, request.getMobile()));
        member.setPhoneHidden(PhoneUtils.hidePhone(request.getMobile()));
        member.setPassword(SecurityUtils.encryptPassword(request.getPassword()));
        member.setNickname("用户" + request.getMobile().substring(7,11));
        member.setStatus(BusinessConstant.MEMBER_ACCOUNT_STATUS.NORMAL);
        member.setGender(0);
        member.setCreateTime(new Date());
       int rows = memberMapper.insert(member);
       if (rows < 1){
           throw new RuntimeException("注册失败，请重试");
       }
       //调用微信授权业务拿到openId等
        WechatUserAuth userToken = wechatAuthService.getUserToken(request.getWechatCode());
       if (userToken == null){
           throw new RuntimeException("授权失败，请重试");
       }
        MemberWechat memberWechat = new MemberWechat();
        memberWechat.setMemberId(member.getId());
        memberWechat.setOpenid(userToken.getOpenid());
        memberWechat.setAccessToken(userToken.getAccess_token());
        memberWechat.setExpiresIn(userToken.getExpires_in());
        memberWechat.setRefreshToken(userToken.getRefresh_token());
        memberWechat.setCreateTime(new Date());
        memberWechat.setCreateBy(member.getId().toString());
        rows = memberWechatMapper.insert(memberWechat);
        if (rows < 1){
            throw new RuntimeException("注册失败，请重试");
        }
        //注册成功直接返回token了
        H5LoginVO loginResponse = getLoginResponse(member.getId());
        response.setToken(loginResponse.getToken());
        return response;
    }

    public ValidatePhoneVO validate(String phone) {
        ValidatePhoneVO response = new ValidatePhoneVO();
        byte[] decodedBytes = Base64.getDecoder().decode(phone);
        phone = new String(decodedBytes);
        QueryWrapper<Member> qw = new QueryWrapper<>();
        qw.eq("phone_encrypted", AesCryptoUtils.encrypt(aesKey, phone));
        Member member = memberMapper.selectOne(qw);
        if (member != null){
            throw new RuntimeException("该手机号已被占用");
        }
        response.setIfSuccess(true);
        response.setMessage("该手机号可用");
        return response;
    }

    /**
     * 账号密码登录
     * @param data
     * @return
     */
    public H5LoginVO accountLogin(String data) {
        if (StrUtil.isEmpty(data)){
            throw new RuntimeException(BusinessConstant.LOGIN_INFO.WRONG);
        }
        // 解码 转 对象
        H5AccountLoginForm request = JSON.parseObject(new String(Base64Utils.decodeFromString(data)), H5AccountLoginForm.class);
        log.info("account login request:{}", JSONUtil.toJsonStr(request));
        QueryWrapper<Member> qw = new QueryWrapper<>();
        qw.eq("phone_encrypted", AesCryptoUtils.encrypt(aesKey, request.getMobile()));
        Member member = memberMapper.selectOne(qw);
        if (member == null){
            throw new RuntimeException(BusinessConstant.LOGIN_INFO.WRONG);
        }
        validateMemberStatus(member);
        //check 密码
        if (!SecurityUtils.matchesPassword(request.getPassword(), member.getPassword())){
            throw new RuntimeException(BusinessConstant.LOGIN_INFO.WRONG);
        }
        return getLoginResponse(member.getId());
    }

    public H5LoginVO smsLogin(String data){
        Date optDate = new Date();
        if (StrUtil.isEmpty(data)){
            throw new RuntimeException(BusinessConstant.LOGIN_INFO.WRONG);
        }
        H5SmsLoginForm request = JSON.parseObject(new String(Base64Utils.decodeFromString(data)), H5SmsLoginForm.class);
        //校验验证码
        this.validateVerifyCode(request.getUuid(), request.getMobile(), request.getCode());
        //查会员
        QueryWrapper<Member> qw = new QueryWrapper<>();
        qw.eq("phone_encrypted", AesCryptoUtils.encrypt(aesKey, request.getMobile()));
        Member member = memberMapper.selectOne(qw);
        if (member == null){
            //新会员，注册并登录
            member = new Member();
            member.setPhoneEncrypted(AesCryptoUtils.encrypt(aesKey, request.getMobile()));
            member.setPhoneHidden(PhoneUtils.hidePhone(request.getMobile()));
            member.setNickname("用户" + request.getMobile().substring(7,11));
            member.setStatus(BusinessConstant.MEMBER_ACCOUNT_STATUS.NORMAL);
            member.setGender(0);
            member.setCreateTime(optDate);
            int rows = memberMapper.insert(member);
            if (rows < 1){
                throw new RuntimeException("注册失败，请重试");
            }
            MemberWechat memberWechat = new MemberWechat();
            memberWechat.setMemberId(member.getId());
            if (request.getAuthInfo() != null){
                memberWechat.setOpenid(request.getAuthInfo().getOpenid());
                memberWechat.setAccessToken(request.getAuthInfo().getAccess_token());
                memberWechat.setExpiresIn(request.getAuthInfo().getExpires_in());
                memberWechat.setRefreshToken(request.getAuthInfo().getRefresh_token());
            }
            if (StrUtil.isNotEmpty(request.getMpOpenId())){
                memberWechat.setRoutineOpenid(request.getMpOpenId());
            }
            memberWechat.setCreateTime(optDate);
            memberWechat.setCreateBy(member.getId().toString());
            rows = memberWechatMapper.insert(memberWechat);
            if (rows < 1){
                throw new RuntimeException("注册失败，请重试");
            }
        }else {
            //校验会员状态
            validateMemberStatus(member);
            //判断小程序openid是否插入
            if (StrUtil.isNotEmpty(request.getMpOpenId()) || request.getAuthInfo() != null){
                QueryWrapper<MemberWechat> queryWrapper = new QueryWrapper();
                queryWrapper.eq("member_id",member.getId());
                MemberWechat memberWechat = memberWechatMapper.selectOne(queryWrapper);
                Boolean update = false;
                if (StrUtil.isNotEmpty(request.getMpOpenId()) && StrUtil.isEmpty(memberWechat.getRoutineOpenid())) {
                    memberWechat.setRoutineOpenid(request.getMpOpenId());
                    update = true;
                }
                if (request.getAuthInfo() != null && StrUtil.isEmpty(memberWechat.getOpenid())) {
                    memberWechat.setOpenid(request.getAuthInfo().getOpenid());
                    memberWechat.setAccessToken(request.getAuthInfo().getAccess_token());
                    memberWechat.setExpiresIn(request.getAuthInfo().getExpires_in());
                    memberWechat.setRefreshToken(request.getAuthInfo().getRefresh_token());
                    update = true;
                }
                if (update){
                    memberWechatMapper.updateById(memberWechat);
                }
            }
        }

        return getLoginResponse(member.getId());
    }

    /**
     * 校验会员状态
     * @param member 会员信息
     */
    private void validateMemberStatus(Member member) {
        if (BusinessConstant.MEMBER_ACCOUNT_STATUS.FORBIDDEN == member.getStatus()){
            throw new RuntimeException(BusinessConstant.LOGIN_INFO.FORBIDDEN);
        }
    }

    /**
     * 校验验证码有效性
     * @param uuid 唯一标识
     * @param phone 手机号
     * @param inputCode 输入的验证码
     */
    private void validateVerifyCode(String uuid, String phone, String inputCode){
        String key = uuid + "_" + phone;
        String redisCode = redisCache.getCacheObject(key);
        if (redisCode ==  null){
            throw new RuntimeException(BusinessConstant.VERIFY_CODE_INFO.EXPIRED);
        }else if (!redisCode.equals(inputCode)){
            throw new RuntimeException(BusinessConstant.VERIFY_CODE_INFO.WRONG);
        }
        //删除缓存
        redisCache.deleteObject(key);
    }

    /**
     * 封装登录响应
     * @param memberId 登录会员id
     * @return 结果
     */
    public H5LoginVO getLoginResponse(Long  memberId){
        LoginMember loginMember = new LoginMember();
        loginMember.setMemberId(memberId);
        String token = tokenService.createMemberToken(loginMember);
        //record登录
        this.insert(memberId);
        H5LoginVO response = new H5LoginVO();
        response.setToken(token);
        return response;
    }

    public MemberVO getMemberInfo() {
        Member member = (Member) LocalDataUtil.getVar(BusinessConstant.MEMBER_INFO);
        MemberVO memberVO = new MemberVO();
        BeanUtils.copyProperties(member, memberVO);
        memberVO.setPhone(AesCryptoUtils.decrypt(aesKey, member.getPhoneEncrypted()));
        QueryWrapper<MemberWechat> qw = new QueryWrapper<>();
        qw.eq("member_id", member.getId());
        MemberWechat memberWechat = memberWechatMapper.selectOne(qw);
        memberVO.setOpenId(memberWechat.getOpenid());
        return memberVO;
    }

    public WechatUserAuth getWechatUserAuth(String data) {
        BindOpenIDForm request = JSON.parseObject(new String(Base64Utils.decodeFromString(data)), BindOpenIDForm.class);
        WechatUserAuth userToken = wechatAuthService.getUserToken(request.getCode());
        if (userToken == null){
            log.error("微信授权失败");
            throw new RuntimeException("授权失败，请重试");
        }
        //判断openid是否存在
        QueryWrapper<MemberWechat> qw = new QueryWrapper<>();
        qw.eq("openid", userToken.getOpenid());
        MemberWechat memberWechat = memberWechatMapper.selectOne(qw);
        if (memberWechat == null) {
            return userToken;
        }
        Member member = memberMapper.selectById(memberWechat.getMemberId());
        userToken.setToken(getLoginResponse(member.getId()).getToken());
        return userToken;
    }

    public void setWechatInfo(String data) {
        WechatUserAuth authInfo = JSON.parseObject(new String(Base64Utils.decodeFromString(data)), WechatUserAuth.class);
        Member member = (Member) LocalDataUtil.getVar(BusinessConstant.MEMBER_INFO);
        UpdateWrapper<MemberWechat> wrapper = new UpdateWrapper<>();
        wrapper.eq("member_id", member.getId());
        wrapper.set("openid", authInfo.getOpenid());
        wrapper.set("access_token", authInfo.getAccess_token());
        wrapper.set("expires_in", authInfo.getExpires_in());
        wrapper.set("refresh_token", authInfo.getRefresh_token());
        wrapper.set("update_time", LocalDateTime.now());
        wrapper.set("update_by", member.getId());
        memberWechatMapper.update(null, wrapper);
    }

    /**
     * 新增会员登录记录
     *
     * @param memberId 会员id
     * @return 结果
     */
    public void insert(Long memberId) {
        Member member = memberMapper.selectById(memberId);
        UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getRequest().getHeader("User-Agent"));
        MemberLogininfor memberLogininfor = new MemberLogininfor();
        memberLogininfor.setMemberId(memberId);
        memberLogininfor.setPhone(member.getPhoneHidden());
        memberLogininfor.setOs(userAgent.getOperatingSystem().getName());
        memberLogininfor.setIpaddr(IpUtils.getIpAddr(ServletUtils.getRequest()));
        memberLogininfor.setBrowser(userAgent.getBrowser().getName());
        memberLogininfor.setLoginLocation(AddressUtils.getRealAddressByIP(memberLogininfor.getIpaddr()));
        memberLogininfor.setLoginTime(LocalDateTime.now());
        memberLogininforMapper.insert(memberLogininfor);
    }

    public H5LoginVO wechatLogin(WechatLoginVO params) throws Exception {
        String openId = params.getOpenId();
        String sessionKey = params.getSessionKey();
        //解密手机号
        String mobile = getMobile(sessionKey, params.getKey(), params.getData());
        if(StrUtil.isEmpty(mobile)) {
            throw new Exception("登录异常");
        }
        Member member = createOrUpdateMember(openId,mobile);
        return getLoginResponse(member.getId());
    }

    private Member createOrUpdateMember(String openId,String mobile){
        //查会员
        QueryWrapper<Member> qw = new QueryWrapper<>();
        qw.eq("phone_encrypted", AesCryptoUtils.encrypt(aesKey, mobile));
        Member member = memberMapper.selectOne(qw);
        if (member == null){
            //新会员，注册并登录
            member = new Member();
            member.setPhoneEncrypted(AesCryptoUtils.encrypt(aesKey, mobile));
            member.setPhoneHidden(PhoneUtils.hidePhone(mobile));
            member.setNickname("用户" + mobile.substring(7,11));
            member.setStatus(BusinessConstant.MEMBER_ACCOUNT_STATUS.NORMAL);
            member.setGender(0);
            member.setCreateTime(new Date());
            int rows = memberMapper.insert(member);
            if (rows < 1){
                throw new RuntimeException("注册失败，请重试");
            }
            MemberWechat memberWechat = new MemberWechat();
            memberWechat.setMemberId(member.getId());
            memberWechat.setRoutineOpenid(openId);
            memberWechat.setCreateTime(new Date());
            memberWechat.setCreateBy(member.getId().toString());
            rows = memberWechatMapper.insert(memberWechat);
            if (rows < 1){
                throw new RuntimeException("注册失败，请重试");
            }
        } else {
            QueryWrapper<MemberWechat> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("member_id", member.getId());
            MemberWechat memberWechat = memberWechatMapper.selectOne(queryWrapper);
            if (StrUtil.isEmpty(memberWechat.getRoutineOpenid())) {
                memberWechat.setRoutineOpenid(openId);
                memberWechatMapper.updateById(memberWechat);
            }
        }
        return member;
    }

    private String getMobile(String sessionKey, String key, String data) {
        AESForWeixinGetPhoneNumber aes = new AESForWeixinGetPhoneNumber(data, sessionKey, key);
        JSONObject decrypt = aes.decrypt();
        if (decrypt != null) {
            return decrypt.getString("phoneNumber");
        }
        return null;
    }

    public Member selectById(Long memberId) {
        return memberMapper.selectById(memberId);
    }
}
