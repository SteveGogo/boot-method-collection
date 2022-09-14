package com.mylearn.springsecuritydemo.auth;

import com.alibaba.fastjson2.JSON;
import com.mylearn.springsecuritydemo.domain.MyUser;
import com.mylearn.springsecuritydemo.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 自定义控制认证逻辑 从DB验证用户
 *
 */
@Service
@Slf4j
public class CustomerUserDetailService implements UserDetailsService {

    @Resource
    private IUserService userService;

    /**
     * 获取用户信息,然后交给spring去校验权限
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //获取用户信息
        MyUser user = userService.getUserRoleByUserName(username);
        if(user == null){
            throw new UsernameNotFoundException("用户名不存在");
        }
        CustomerUserDetails customerUserDetails = new CustomerUserDetails(user);

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        //用于添加用户的权限。只要把用户权限添加到authorities 就万事大吉。
        if (!CollectionUtils.isEmpty(user.getRoles())){
            user.getRoles().forEach(r -> authorities.add(new SimpleGrantedAuthority("ROLE_"+r.getRoleName())));
        }
        customerUserDetails.setAuthorities(authorities);
        log.info("authorities:{}", JSON.toJSONString(authorities));

        //这里返回的是我们自己定义的UserDetail
        return customerUserDetails;
    }
}