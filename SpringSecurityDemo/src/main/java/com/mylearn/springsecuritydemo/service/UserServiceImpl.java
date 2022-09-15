package com.mylearn.springsecuritydemo.service;

import com.mylearn.springsecuritydemo.domain.MyUser;
import com.mylearn.springsecuritydemo.domain.Role;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    /**
     * 按用户名查询USER
     */
    @Override
    public MyUser getUserRoleByUserName(String username) {
        MyUser myUser = new MyUser();
        myUser.setId(1);
        myUser.setUsername("admin");
        myUser.setPassword("123");
        //获取角色
        myUser.setRoles(getRoles(myUser.getId()));
        return myUser;
    }

    private List<Role> getRoles(Integer id) {
        List<Role> roles = new ArrayList<>();
        roles.add(Role.builder().roleName("admin").build());
        roles.add(Role.builder().roleName("user").build());
        return roles;
    }
}
