package com.learn.springsecuritymyimpl.security;

import com.alibaba.fastjson2.JSON;
import com.learn.springsecuritymyimpl.common.R;
import com.learn.springsecuritymyimpl.common.Result;
import com.learn.springsecuritymyimpl.common.ResultCode;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 很多功能是需要登录了才能访问的，
 * 所以下面就是未登录的一个处理
 */
@Component
public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        R result = Result.Error(ResultCode.USER_NOT_LOGIN);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(result));

    }
}
