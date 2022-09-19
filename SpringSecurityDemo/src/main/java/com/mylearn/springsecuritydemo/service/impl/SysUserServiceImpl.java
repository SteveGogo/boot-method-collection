package com.mylearn.springsecuritydemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mylearn.springsecuritydemo.entity.SysUser;
import com.mylearn.springsecuritydemo.mapper.SysUserMapper;
import com.mylearn.springsecuritydemo.service.ISysUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Resource
    private SysUserMapper userMapper;

    @Override
    public SysUser loadUserByUsername(String username) {
        return userMapper.selectByName(username);
    }

    @Override
    public void update(SysUser sysUser) {
        baseMapper.updateById(sysUser);
    }
}
