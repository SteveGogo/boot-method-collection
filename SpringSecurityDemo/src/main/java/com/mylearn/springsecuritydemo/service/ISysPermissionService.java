package com.mylearn.springsecuritydemo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mylearn.springsecuritydemo.entity.SysPermission;

import java.util.List;

/**
 * <p>
 * 权限表 服务类
 * </p>
 *
 * @author steve
 * @since 2022-09-16
 */
public interface ISysPermissionService extends IService<SysPermission> {
    List<SysPermission> selectListByUser(Integer userId);

}
