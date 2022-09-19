package com.mylearn.springsecuritydemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mylearn.springsecuritydemo.entity.SysPermission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 权限表 Mapper 接口
 * </p>
 *
 * @author steve
 * @since 2022-09-16
 */
@Mapper
public interface SysPermissionMapper extends BaseMapper<SysPermission> {
    List<SysPermission> selectListByUser(Integer userId);

    List<SysPermission> selectListByPath(String url);
}
