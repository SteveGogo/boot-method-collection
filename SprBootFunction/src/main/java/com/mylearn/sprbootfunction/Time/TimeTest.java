package com.mylearn.sprbootfunction.Time;

import java.time.LocalDateTime;

public class TimeTest {
    public static void main(String[] args) {

        LocalDateTime now = LocalDateTime.now();

        System.out.println(now.toLocalDate());
        System.out.println(now);
    }
}
