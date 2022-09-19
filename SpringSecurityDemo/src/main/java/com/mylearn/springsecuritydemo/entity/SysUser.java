package com.mylearn.springsecuritydemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysUser {
    private Integer id;

    private String account;

    private String userName;

    private String password;

    private LocalDateTime lastLoginTime;

    private Boolean enabled;
    private Boolean accountNonExpired;
    private Boolean accountNonLocked;
    private Boolean credentialsNonExpired;


    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    private Integer createUser;
    private Integer updateUser;
}
