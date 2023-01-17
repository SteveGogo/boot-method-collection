package com.learn.springsecuritymyimpl.security;

import com.alibaba.fastjson2.JSON;
import com.learn.springsecuritymyimpl.common.R;
import com.learn.springsecuritymyimpl.common.Result;
import com.learn.springsecuritymyimpl.common.ResultCode;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        R result = Result.Ok(ResultCode.SUCCESS_logout);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(result));
    }
}
