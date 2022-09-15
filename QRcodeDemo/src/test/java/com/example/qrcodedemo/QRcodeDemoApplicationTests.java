package com.example.qrcodedemo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class QRcodeDemoApplicationTests {

    @Resource
    private QRCodeService qrCodeService;

    @Test
    void contextLoads() {
    }

}
