package com.mylearn.sprbootfunction.Time;

import cn.hutool.core.date.LocalDateTimeUtil;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class TimeTest {
    public static void main(String[] args) {

        LocalDateTime day2= LocalDateTimeUtil.of(1651029258, ZoneOffset.ofHours(8));

        System.out.println(day2);

    }
}
