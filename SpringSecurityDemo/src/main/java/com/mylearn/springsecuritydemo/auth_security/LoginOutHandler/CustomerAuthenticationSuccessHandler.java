package com.mylearn.springsecuritydemo.auth_security.LoginOutHandler;

import com.alibaba.fastjson2.JSON;
import com.mylearn.springsecuritydemo.auth_security.userDetailInfo.CustomerUserDetails;
import com.mylearn.springsecuritydemo.common.JsonResult;
import com.mylearn.springsecuritydemo.common.ResultTool;
import com.mylearn.springsecuritydemo.entity.SysUser;
import com.mylearn.springsecuritydemo.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * 登录成功处理类,登录成功后会调用里面的方法
 */
@Slf4j
@Component
public class CustomerAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Resource
    private ISysUserService sysUserService;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        //简单的说就是获取当前用户，拿到用户名或者userId，创建token，返回
        log.info("登陆成功...");
        //CustomerUserDetails principal = (CustomerUserDetails) authentication.getPrincipal();
        ////颁发token
        //Map<String,Object> emptyMap = new HashMap<>(4);
        //emptyMap.put(UserConstants.USER_ID,principal.getId());
        //String token = JwtTokenUtil.generateToken(principal.getUsername(), emptyMap);
        //ResponseUtil.out(ResultUtil.success(token));

        //更新用户表上次登录时间、更新人、更新时间等字段
        CustomerUserDetails userDetails = (CustomerUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        SysUser sysUser = sysUserService.loadUserByUsername(userDetails.getUsername());
        sysUser.setLastLoginTime(LocalDateTime.now());
        sysUser.setUpdateTime(LocalDateTime.now());
        sysUser.setUpdateUser(sysUser.getId());
        sysUserService.update(sysUser);
        //此处还可以进行一些处理，比如登录成功之后可能需要返回给前台当前用户有哪些菜单权限，
        //进而前台动态的控制菜单的显示等，具体根据自己的业务需求进行扩展[token等]
        //返回json数据
        JsonResult result = ResultTool.success();
        //处理编码方式，防止中文乱码的情况
        response.setContentType("text/json;charset=utf-8");
        //塞到HttpServletResponse中返回给前台
        response.getWriter().write(JSON.toJSONString(result));
    }
}
