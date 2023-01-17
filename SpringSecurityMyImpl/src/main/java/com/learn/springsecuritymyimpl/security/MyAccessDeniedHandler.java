package com.learn.springsecuritymyimpl.security;

import com.alibaba.fastjson2.JSON;
import com.learn.springsecuritymyimpl.common.R;
import com.learn.springsecuritymyimpl.common.Result;
import com.learn.springsecuritymyimpl.common.ResultCode;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 用户访问没有权限的接口,访问别拒绝
 */
@Component
public class MyAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        R r = Result.Error(ResultCode.no_permission);
        response.setContentType("text/json;charset=utf-8");
        // 把Json数据放到HttpServletResponse中返回给前台
        response.getWriter().write(JSON.toJSONString(r));
    }
}
