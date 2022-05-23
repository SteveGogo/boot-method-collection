//package com.mylearn.springsecuritydemo.filter;
//
//import lombok.extern.slf4j.Slf4j;
//import org.apache.tomcat.util.http.ResponseUtil;
//import org.springframework.security.access.AccessDeniedException;
//import org.springframework.security.web.access.AccessDeniedHandler;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///**
// * 没有权限,被拒绝访问时的调用类
// */
//@Component
//@Slf4j
//public class CustomerRestAccessDeniedHandler implements AccessDeniedHandler {
//
//    @Override
//    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) {
//        ResponseUtil.out(403, ResultUtil.failure(ErrorCodeConstants.PERMISSION_DENY));
//    }
//
//}
