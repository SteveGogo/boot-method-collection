package com.example.applicationdemo.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/test")
public class Hello {


    @GetMapping("/hello")
    private String hello(){
        return "hello";
    }


    @GetMapping("/timeout")
    public String getTimeout(Integer id) throws InterruptedException {
        if (id == 1) {
            return "success";
        } else if (id == 2) {
            Thread.currentThread().sleep(1800);
            return "2";
        } else if (id == 3) {
            Thread.currentThread().sleep(2000);
            return "3";
        } else {
            Thread.currentThread().sleep(61000);
            return "fail";
        }
    }

    @RequestMapping(value="/customSleep",name="customSleep")
    public String customSleep(int n) {

        try {
            log.info("sleep {} seconds",n);
            Thread.sleep(n*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return "InterruptedException";
        }
        return "success";
    }


}
