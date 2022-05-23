//package com.mylearn.springsecuritydemo.filter;
//
//import lombok.extern.slf4j.Slf4j;
//import org.apache.tomcat.util.http.ResponseUtil;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.AuthenticationFailureHandler;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// * 登录账号密码错误等情况下,会调用的处理类
// */
//@Slf4j
//@Component
//public class CustomerAuthenticationFailHandler implements AuthenticationFailureHandler {
//
//
//    @Override
//    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
//        //设置response状态码，返回错误信息等
//     ....
//        ResponseUtil.out(401, ResultUtil.failure(ErrorCodeConstants.LOGIN_UNMATCH_ERROR));
//    }
//
//}
