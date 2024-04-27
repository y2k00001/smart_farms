package com.neo.farmlands.config.interceptor;

import com.neo.common.constant.HttpStatus;
import com.neo.common.core.domain.model.LoginMember;
import com.neo.common.exception.ServiceException;
import com.neo.farmlands.constant.BusinessConstant;
import com.neo.farmlands.domain.entity.Member;
import com.neo.farmlands.service.impl.MemberService;
import com.neo.framework.config.LocalDataUtil;
import com.neo.framework.web.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
public class H5MemberInterceptor extends HandlerInterceptorAdapter {

  @Autowired
  private TokenService tokenService;
  @Autowired
  private MemberService memberService;

  private static String[] WHITE_PATHS = {
          "/h5/sms/login",
          "/h5/wechat/login",
          "/h5/account/login",
          "/h5/register",
          "/h5/validate"
  };

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    String requestUri = request.getRequestURI();
    boolean flag = true;
    if (!requestUri.startsWith("/h5/")) {
      return super.preHandle(request, response, handler);
    }

    for (String s : WHITE_PATHS) {
      if (requestUri.startsWith(s)) {
        flag = false;
        break;
      }
    }
    if (!flag) {
      return super.preHandle(request, response, handler);
    }
    LoginMember loginMember = tokenService.getLoginMember(request);
    if (loginMember == null) {
      throw new ServiceException("获取用户ID异常", HttpStatus.UNAUTHORIZED);
    }
    tokenService.verifyMemberToken(loginMember);
    //获取会员信息
    Member member = memberService.selectById(loginMember.getMemberId());
    if (member == null || member.getStatus() == 0) {
      throw new ServiceException("获取用户ID异常", HttpStatus.UNAUTHORIZED);
    }
    //将会员信息存放至全局
    LocalDataUtil.setVar(BusinessConstant.MEMBER_INFO, member);

    return super.preHandle(request, response, handler);
  }



}
