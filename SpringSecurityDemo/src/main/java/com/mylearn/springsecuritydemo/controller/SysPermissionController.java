package com.mylearn.springsecuritydemo.controller;

import com.mylearn.springsecuritydemo.entity.SysPermission;
import com.mylearn.springsecuritydemo.service.ISysPermissionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 权限表 前端控制器
 * </p>
 *
 * @author steve
 * @since 2022-09-16
 */
@RestController
public class SysPermissionController {
    @Resource
    private ISysPermissionService sysPermissionService;

    @GetMapping("/sysPermission")
    public List<SysPermission> getPermissions(Integer userId) {
        return sysPermissionService.selectListByUser(userId);
    }
}
