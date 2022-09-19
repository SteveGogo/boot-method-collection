package com.mylearn.springsecuritydemo.service;

import com.mylearn.springsecuritydemo.entity.SysUser;

public interface ISysUserService {

    SysUser loadUserByUsername(String username);

    void update(SysUser sysUser);
}
