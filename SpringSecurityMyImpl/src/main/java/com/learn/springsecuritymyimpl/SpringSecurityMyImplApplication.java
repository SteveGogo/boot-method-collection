package com.learn.springsecuritymyimpl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.learn.springsecuritymyimpl.mapper")
public class SpringSecurityMyImplApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityMyImplApplication.class, args);
    }

}

