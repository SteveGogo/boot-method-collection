package com.mylearn.springsecuritydemo.service;

import com.mylearn.springsecuritydemo.domain.MyUser;

public interface IUserService {
    MyUser getUserRoleByUserName(String username);

}
