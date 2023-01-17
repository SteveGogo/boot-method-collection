package com.learn.springsecuritymyimpl.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.learn.springsecuritymyimpl.entity.SysRole;
import com.learn.springsecuritymyimpl.service.SysRoleService;
import com.learn.springsecuritymyimpl.mapper.SysRoleMapper;
import org.springframework.stereotype.Service;

/**
* @author steve_gao
* @description 针对表【sys_role(用户角色表)】的数据库操作Service实现
* @createDate 2023-01-15 16:12:03
*/
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole>
    implements SysRoleService{

}




