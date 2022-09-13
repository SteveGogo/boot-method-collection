package com.example.redislockdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 自定义注解方式实现分布式锁
 */
@SpringBootApplication
public class RedisLockDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(RedisLockDemoApplication.class, args);
    }

}
