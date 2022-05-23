//package com.mylearn.springsecuritydemo.auth;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @description 自己实现UserDetailService,用与SpringSecurity获取用户信息
// */
//@Service
//@Slf4j
//public class CustomerUserDetailService implements UserDetailsService {
//
//    @Autowired
//    private IUserService userService;
//
//    /**
//     * 获取用户信息,然后交给spring去校验权限
//     * @param username
//     * @return
//     * @throws UsernameNotFoundException
//     */
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        //获取用户信息
//        User user = userService.getUserRoleByUserName(username);
//        if(user == null){
//            throw new UsernameNotFoundException("用户名不存在");
//        }
//        CustomerUserDetails customerUserDetails = new CustomerUserDetails(user);
//
//        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
//        //用于添加用户的权限。只要把用户权限添加到authorities 就万事大吉。
//        if (CollectionUtils.isNotEmpty(user.getRoles())){
//            user.getRoles().forEach(r -> authorities.add(new SimpleGrantedAuthority("ROLE_"+r.getRoleName())));
//        }
//        customerUserDetails.setAuthorities(authorities);
//        log.info("authorities:{}", JSON.toJSONString(authorities));
//
//        //这里返回的是我们自己定义的UserDetail
//        return customerUserDetails;
//    }
//}