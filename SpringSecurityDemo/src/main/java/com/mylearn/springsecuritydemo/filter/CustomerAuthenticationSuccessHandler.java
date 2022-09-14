package com.mylearn.springsecuritydemo.filter;

import com.mylearn.springsecuritydemo.auth.CustomerUserDetails;
import com.mylearn.springsecuritydemo.constant.UserConstants;
import com.mylearn.springsecuritydemo.util.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.ResponseUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录成功处理类,登录成功后会调用里面的方法
 */
@Slf4j
@Component
public class CustomerAuthenticationSuccessHandler implements AuthenticationSuccessHandler {


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        //简单的说就是获取当前用户，拿到用户名或者userId，创建token，返回
        log.info("登陆成功...");
        CustomerUserDetails principal = (CustomerUserDetails) authentication.getPrincipal();
        //颁发token
        Map<String,Object> emptyMap = new HashMap<>(4);
        emptyMap.put(UserConstants.USER_ID,principal.getId());
        String token = JwtTokenUtil.generateToken(principal.getUsername(), emptyMap);
        ResponseUtil.out(ResultUtil.success(token));
    }
}
