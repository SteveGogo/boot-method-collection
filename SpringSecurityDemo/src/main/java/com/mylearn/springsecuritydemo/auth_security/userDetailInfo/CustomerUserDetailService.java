package com.mylearn.springsecuritydemo.auth_security.userDetailInfo;

import com.alibaba.fastjson2.JSON;
import com.mylearn.springsecuritydemo.entity.SysPermission;
import com.mylearn.springsecuritydemo.entity.SysUser;
import com.mylearn.springsecuritydemo.service.ISysPermissionService;
import com.mylearn.springsecuritydemo.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 自定义控制认证逻辑 从DB验证用户
 */
@Service
@Slf4j
public class CustomerUserDetailService implements UserDetailsService {

    @Resource
    private ISysUserService userService;
    @Resource
    private ISysPermissionService sysPermissionService;

    /**
     * 获取用户信息,然后交给spring去校验权限
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username == null || "".equals(username)) {
            throw new RuntimeException("用户不能为空");
        }
        //根据用户名查询用户
        SysUser sysUser = userService.loadUserByUsername(username);
        if (sysUser == null) {
            throw new RuntimeException("用户不存在");
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        if (sysUser != null) {
            //用于添加用户的权限。只要把用户权限添加到authorities 就万事大吉
            List<SysPermission> sysPermissions = sysPermissionService.selectListByUser(sysUser.getId());
            // 声明用户授权
            sysPermissions.forEach(sysPermission -> {
                SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority(sysPermission.getPermissionCode());
                authorities.add(grantedAuthority);
            });
        }
        log.info("authorities:{}", JSON.toJSONString(authorities));
        //这里返回的是我们自己定义的UserDetail
        //return new User(sysUser.getAccount(), sysUser.getPassword(), sysUser.getEnabled(), sysUser
        // .getAccountNonExpired(), sysUser.getCredentialsNonExpired(), sysUser.getAccountNonLocked(), authorities);
        return new CustomerUserDetails(sysUser, authorities);
    }
}