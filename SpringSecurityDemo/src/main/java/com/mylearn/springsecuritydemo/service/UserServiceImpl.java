package com.mylearn.springsecuritydemo.service;

import com.mylearn.springsecuritydemo.domain.MyUser;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

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
        myUser.setRoles(new ArrayList<>());
        return myUser;
    }
}
