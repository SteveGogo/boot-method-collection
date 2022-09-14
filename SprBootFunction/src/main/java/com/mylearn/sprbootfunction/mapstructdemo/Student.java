package com.mylearn.sprbootfunction.mapstructdemo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private String name;
    private int age;
    private GenderEnum gender;
    private Double height;
    private Date birthday;
}


