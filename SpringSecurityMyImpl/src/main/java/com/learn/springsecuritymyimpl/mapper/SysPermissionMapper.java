package com.learn.springsecuritymyimpl.mapper;

import com.learn.springsecuritymyimpl.entity.SysPermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author steve_gao
* @description 针对表【sys_permission(权限表)】的数据库操作Mapper
* @createDate 2023-01-15 16:12:03
* @Entity com.learn.springsecuritymyimpl.entity.SysPermission
*/
@Mapper
public interface SysPermissionMapper extends BaseMapper<SysPermission> {

    List<SysPermission> getUserRolesByUserId(Integer userId);

    List<SysPermission> selectListByPath(String requestUrl);
}




