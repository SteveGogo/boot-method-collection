package com.mylearn.springsecuritydemo.Handler;


import com.mylearn.springsecuritydemo.common.ResponseUtil;
import com.mylearn.springsecuritydemo.common.ResultUtil;
import com.mylearn.springsecuritydemo.constant.ErrorCodeConstants;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 匿名未登录的时候访问,需要登录的资源的调用类
 */
@Component
public class CustomerAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        ResponseUtil.out(401, ResultUtil.failure(ErrorCodeConstants.REQUIRED_LOGIN_ERROR));
    }
}
