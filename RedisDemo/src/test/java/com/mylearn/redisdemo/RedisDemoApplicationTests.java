package com.mylearn.redisdemo;

import com.mylearn.redisdemo.util.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest
class RedisDemoApplicationTests {
    @Resource
    private RedisUtil redisUtil;

    @Test
    void contextLoads() {
        //按“,”分割字符串，返回一个List
        List<String> list = new ArrayList<>();
        list.add("a,b,c");
        list.add("1,2,3");
        List<String> collect = list.stream().flatMap(x -> {
            String[] split = x.split(",");
              return Stream.of(split);
        }).collect(Collectors.toList());
        System.out.println(collect);
    }

}

