package com.example.redislockdemo.Controller;

import com.example.redislockdemo.annotation.RedisLockAnnotation;
import com.example.redislockdemo.emun.RedisLockTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.awt.print.Book;


@RestController
@Slf4j
public class LockController {
    @GetMapping("/testRedisLock")
    @RedisLockAnnotation(typeEnum = RedisLockTypeEnum.ONE, lockTime = 3)
    public String testRedisLock(@RequestParam("userId") Long userId) {
        try {
            log.info("睡眠执行前");
            Thread.sleep(10000);
            log.info("睡眠执行后");
        } catch (Exception e) {
            // log error
            log.info("has some error", e);
        }
        return null;
    }

}
