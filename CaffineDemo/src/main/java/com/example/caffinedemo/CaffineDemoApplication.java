package com.example.caffinedemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CaffineDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CaffineDemoApplication.class, args);
        System.out.println(SpringBootVersion.getVersion());
    }

}
