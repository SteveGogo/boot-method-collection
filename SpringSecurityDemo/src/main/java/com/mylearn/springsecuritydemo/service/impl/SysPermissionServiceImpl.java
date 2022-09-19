package com.mylearn.springsecuritydemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mylearn.springsecuritydemo.entity.SysPermission;
import com.mylearn.springsecuritydemo.mapper.SysPermissionMapper;
import com.mylearn.springsecuritydemo.service.ISysPermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author steve
 * @since 2022-09-16
 */
@Service
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements ISysPermissionService {

    @Resource
    private SysPermissionMapper sysPermissionMapper;


    @Override
    public List<SysPermission> selectListByUser(Integer userId) {
        return sysPermissionMapper.selectListByUser(userId);
    }
}
