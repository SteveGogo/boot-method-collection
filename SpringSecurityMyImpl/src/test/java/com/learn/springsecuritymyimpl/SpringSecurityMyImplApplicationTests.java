package com.learn.springsecuritymyimpl;

import com.learn.springsecuritymyimpl.utils.RedisUtil;
import io.jsonwebtoken.impl.TextCodec;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class SpringSecurityMyImplApplicationTests {

    @Autowired
    private RedisUtil redisUtil;

    @Test
    void contextLoads() {
    }

    @Test
    void redisTest() {
        byte[] bytes = TextCodec.BASE64.decode("MyJwtSecret");
        System.out.println(bytes);

    }

    @Test
    void test2() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode("123456");
        System.out.println(encode);

    }


}
