package com.example.logbackdemo;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LogbackDemoApplicationTests {

    @Test
    void contextLoads() {
    }


    Logger logger = LoggerFactory.getLogger(getClass());
    @Test
    void contextLoads1() {
        //日志的级别从低到高
        logger.trace("这是trace日志。。。");
        logger.debug("这是dubug日志。。。");
        logger.info("这是info日志。。。");
        logger.warn("这是warn日志。。。");
        logger.error("这是error日志");
    }

}
