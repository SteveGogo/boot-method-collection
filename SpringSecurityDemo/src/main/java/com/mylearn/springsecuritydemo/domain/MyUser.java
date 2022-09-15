package com.mylearn.springsecuritydemo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyUser {

    private Integer id;

    private String username;

    private String password;

    private Integer status;

    List<Role> roles;
}
