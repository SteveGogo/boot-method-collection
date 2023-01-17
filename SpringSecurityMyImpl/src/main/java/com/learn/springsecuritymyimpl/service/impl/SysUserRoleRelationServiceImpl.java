package com.learn.springsecuritymyimpl.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.learn.springsecuritymyimpl.entity.SysUserRoleRelation;
import com.learn.springsecuritymyimpl.service.SysUserRoleRelationService;
import com.learn.springsecuritymyimpl.mapper.SysUserRoleRelationMapper;
import org.springframework.stereotype.Service;

/**
* @author steve_gao
* @description 针对表【sys_user_role_relation(用户角色关联关系表)】的数据库操作Service实现
* @createDate 2023-01-15 16:12:03
*/
@Service
public class SysUserRoleRelationServiceImpl extends ServiceImpl<SysUserRoleRelationMapper, SysUserRoleRelation>
    implements SysUserRoleRelationService{

}




