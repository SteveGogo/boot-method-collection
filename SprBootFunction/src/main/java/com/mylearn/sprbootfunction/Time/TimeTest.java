package com.mylearn.sprbootfunction.Time;

import cn.hutool.core.date.LocalDateTimeUtil;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class TimeTest {
//    public static void main(String[] args) {
//
//        LocalDateTime now = LocalDateTime.now();
//
//        System.out.println(now.toLocalDate());
//        System.out.println(now);
//    }

    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime next = now.plusHours(1);
        int between = (int) LocalDateTimeUtil.between(LocalDateTime.now(), next, ChronoUnit.SECONDS);
        System.out.println(between);
    }


}
