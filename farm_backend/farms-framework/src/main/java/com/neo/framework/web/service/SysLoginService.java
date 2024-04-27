package com.neo.framework.web.service;

import javax.annotation.Resource;

import cn.hutool.core.util.RandomUtil;
import com.neo.common.core.domain.ExtraUserBody;
import com.neo.common.core.domain.entity.SysRole;
import com.neo.common.utils.*;
import com.neo.framework.manager.AsyncManager;
import com.neo.framework.manager.factory.AsyncFactory;
import com.neo.system.service.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import com.neo.common.constant.CacheConstants;
import com.neo.common.constant.Constants;
import com.neo.common.constant.UserConstants;
import com.neo.common.core.domain.entity.SysUser;
import com.neo.common.core.domain.model.LoginUser;
import com.neo.common.core.redis.RedisCache;
import com.neo.common.exception.ServiceException;
import com.neo.common.exception.user.BlackListException;
import com.neo.common.exception.user.CaptchaException;
import com.neo.common.exception.user.CaptchaExpireException;
import com.neo.common.exception.user.UserNotExistsException;
import com.neo.common.exception.user.UserPasswordNotMatchException;
import com.neo.common.utils.ip.IpUtils;
import com.neo.framework.security.context.AuthenticationContextHolder;
import com.neo.system.service.ISysConfigService;
import com.neo.system.service.ISysUserService;

import java.util.Collections;
import java.util.Date;

/**
 * 登录校验方法
 *
 * @author ruoyi
 */
@Component
public class SysLoginService
{
    @Autowired
    private TokenService tokenService;

    @Resource
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private ISysUserService userService;

    @Autowired
    private ISysConfigService configService;

    @Resource
    private ISysRoleService sysRoleService;

    @Resource
    private SysPermissionService permissionService;

    /**
     * 登录验证
     *
     * @param username 用户名
     * @param password 密码
     * @param code     验证码
     * @param uuid     唯一标识
     * @return 结果
     */
    public String login(String username, String password, String code, String uuid) {
        boolean captchaOnOff = configService.selectCaptchaOnOff();
        // 验证码开关
        if (captchaOnOff) {
            validateCaptcha(username, code, uuid);
        }
        // 用户验证
        Authentication authentication = null;
        try {
            // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(username, password));
        }
        catch (Exception e)
        {
            if (e instanceof BadCredentialsException)
            {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match")));
                throw new UserPasswordNotMatchException();
            }
            else
            {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, e.getMessage()));
                throw new ServiceException(e.getMessage());
            }
        }
        AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_SUCCESS, MessageUtils.message("user.login.success")));
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        recordLoginInfo(loginUser.getUser());
        // 生成token
        return tokenService.createToken(loginUser);
    }

    /**
     * 校验验证码
     *
     * @param username 用户名
     * @param code 验证码
     * @param uuid 唯一标识
     * @return 结果
     */
    public void validateCaptcha(String username, String code, String uuid)
    {
        boolean captchaEnabled = configService.selectCaptchaEnabled();
        if (captchaEnabled)
        {
            String verifyKey = CacheConstants.CAPTCHA_CODE_KEY + StringUtils.nvl(uuid, "");
            String captcha = redisCache.getCacheObject(verifyKey);
            redisCache.deleteObject(verifyKey);
            if (captcha == null)
            {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.expire")));
                throw new CaptchaExpireException();
            }
            if (!code.equalsIgnoreCase(captcha))
            {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.error")));
                throw new CaptchaException();
            }
        }
    }

    /**
     * 登录前置校验
     * @param username 用户名
     * @param password 用户密码
     */
    public void loginPreCheck(String username, String password)
    {
        // 用户名或密码为空 错误
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password))
        {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("not.null")));
            throw new UserNotExistsException();
        }
        // 密码如果不在指定范围内 错误
        if (password.length() < UserConstants.PASSWORD_MIN_LENGTH
                || password.length() > UserConstants.PASSWORD_MAX_LENGTH)
        {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match")));
            throw new UserPasswordNotMatchException();
        }
        // 用户名不在指定范围内 错误
        if (username.length() < UserConstants.USERNAME_MIN_LENGTH
                || username.length() > UserConstants.USERNAME_MAX_LENGTH)
        {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match")));
            throw new UserPasswordNotMatchException();
        }
        // IP黑名单校验
        String blackStr = configService.selectConfigByKey("sys.login.blackIPList");
        if (IpUtils.isMatchedIp(blackStr, IpUtils.getIpAddr()))
        {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("login.blocked")));
            throw new BlackListException();
        }
    }

    /**
     * 记录登录信息
     */
    public void recordLoginInfo(SysUser user) {
        user.setLoginIp(IpUtils.getIpAddr(ServletUtils.getRequest()));
        user.setLoginDate(DateUtils.getNowDate());
        userService.updateUserProfile(user);
    }

    public SysUser initVipUser(ExtraUserBody body) {
        Date now = new Date();
        SysUser user = new SysUser();
        user.setNickName(body.getNickname());
        user.setUserName(body.getLogin());
        user.setAvatar(body.getAvatar());
        user.setLoginDate(now);
        user.setPassword(SecurityUtils.encryptPassword(RandomUtil.randomString(16)));

        // 注册用户
        userService.registerUser(user);

        // 赋予角色
        SysRole role = sysRoleService.selectRoleByKey("common");
        // 增加用户的权限，绑定角色
        sysRoleService.insertAuthUsers(role.getRoleId(), new Long[]{user.getUserId()});

        AsyncManager.me().execute(AsyncFactory.recordLogininfor(body.getLogin(), Constants.LOGIN_SUCCESS, MessageUtils.message("user.register.success")));
        recordLoginInfo(user);
        user.setRoles(Collections.singletonList(role));
        return user;
    }

    public String createToken(SysUser user) {
        LoginUser loginUser = new LoginUser(user.getUserId(), user.getDeptId(), user, permissionService.getMenuPermission(user));
        return tokenService.createToken(loginUser);
    }
}
