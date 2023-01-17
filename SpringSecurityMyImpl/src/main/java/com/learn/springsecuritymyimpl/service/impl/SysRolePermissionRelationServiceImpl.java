package com.learn.springsecuritymyimpl.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.learn.springsecuritymyimpl.entity.SysRolePermissionRelation;
import com.learn.springsecuritymyimpl.service.SysRolePermissionRelationService;
import com.learn.springsecuritymyimpl.mapper.SysRolePermissionRelationMapper;
import org.springframework.stereotype.Service;

/**
* @author steve_gao
* @description 针对表【sys_role_permission_relation(角色-权限关联关系表)】的数据库操作Service实现
* @createDate 2023-01-15 16:12:03
*/
@Service
public class SysRolePermissionRelationServiceImpl extends ServiceImpl<SysRolePermissionRelationMapper, SysRolePermissionRelation>
    implements SysRolePermissionRelationService{

}




