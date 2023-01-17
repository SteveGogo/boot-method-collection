package com.learn.springsecuritymyimpl.service;

import com.learn.springsecuritymyimpl.entity.SysPermission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author steve_gao
* @description 针对表【sys_permission(权限表)】的数据库操作Service
* @createDate 2023-01-15 16:12:03
*/
public interface SysPermissionService extends IService<SysPermission> {

    List<SysPermission> getUserRolesByUserId(Integer userId);
}
