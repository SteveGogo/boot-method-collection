package com.mylearn.springsecuritydemo.auth_security.access;

import com.alibaba.fastjson2.JSON;
import com.mylearn.springsecuritydemo.common.JsonResult;
import com.mylearn.springsecuritydemo.common.ResultCode;
import com.mylearn.springsecuritydemo.common.ResultTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 没有权限,被拒绝访问时的调用类
 */
@Component
@Slf4j
public class CustomerAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        //ResponseUtil.out(403, ResultUtil.failure(ErrorCodeConstants.PERMISSION_DENY));
        JsonResult result = ResultTool.fail(ResultCode.NO_PERMISSION);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(result));
    }

}
