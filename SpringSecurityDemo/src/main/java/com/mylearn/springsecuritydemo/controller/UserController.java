package com.mylearn.springsecuritydemo.controller;

import com.mylearn.springsecuritydemo.entity.SysUser;
import com.mylearn.springsecuritydemo.service.ISysUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class UserController {
    @Resource
    private ISysUserService userService;

    @GetMapping("/user")
    public SysUser getUser(String username){
        SysUser userRoleByUserName = userService.loadUserByUsername(username);
        return userRoleByUserName;
    }

    @GetMapping("/getUser")
    public SysUser getUser2(String username){
        SysUser userRoleByUserName = userService.loadUserByUsername(username);
        return userRoleByUserName;
    }


}
