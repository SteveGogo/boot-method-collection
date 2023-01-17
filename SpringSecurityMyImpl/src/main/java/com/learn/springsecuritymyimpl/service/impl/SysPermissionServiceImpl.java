package com.learn.springsecuritymyimpl.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.learn.springsecuritymyimpl.entity.SysPermission;
import com.learn.springsecuritymyimpl.service.SysPermissionService;
import com.learn.springsecuritymyimpl.mapper.SysPermissionMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author steve_gao
* @description 针对表【sys_permission(权限表)】的数据库操作Service实现
* @createDate 2023-01-15 16:12:03
*/
@Service
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission>
    implements SysPermissionService{

    @Override
    public List<SysPermission> getUserRolesByUserId(Integer userId) {
        return baseMapper.getUserRolesByUserId(userId);
    }
}




