package com.learn.springsecuritymyimpl.security;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.learn.springsecuritymyimpl.common.R;
import com.learn.springsecuritymyimpl.common.Result;
import com.learn.springsecuritymyimpl.common.ResultCode;
import com.learn.springsecuritymyimpl.entity.SysUser;
import com.learn.springsecuritymyimpl.mapper.SysUserMapper;
import com.learn.springsecuritymyimpl.utils.JwtTokenUtilUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录成功处理器
 */
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private SysUserMapper mapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        //更新用户表上次登录时间、更新人、更新时间等字段
        User userDetails = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        SysUser sysUser = mapper.selectOne(new QueryWrapper<SysUser>().eq("account", userDetails.getUsername()));
        sysUser.setLastLoginTime(new Date());
        sysUser.setUpdateTime(new Date());
        sysUser.setUpdateUser(sysUser.getId());
        mapper.update(sysUser, new QueryWrapper<SysUser>().eq("id", sysUser.getId()));

        // 根据用户的id和account生成token并返回
        String jwtToken = JwtTokenUtilUtil.createToken(sysUser.getId().toString(), sysUser.getAccount(), null);
        Map<String, String> token = new HashMap<>();
        token.put("token", jwtToken);

        //返回json数据
        R result = Result.Ok(ResultCode.SUCCESS_login, token);
        //处理编码方式，防止中文乱码的情况
        response.setContentType("text/json;charset=utf-8");
        // 把Json数据放入HttpServletResponse中返回给前台
        response.getWriter().write(JSON.toJSONString(result));
    }
}
