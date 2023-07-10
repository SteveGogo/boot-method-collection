package com.mylearn.sprbootfunction.StreamDemo;

import java.util.List;

public class IteratorDemo {
    public static void main(String[] args) {
        List<String> list = List.of("a", "b", "c");

        for (String s : list) {
            if (s.equals("b")) {
                continue;
            }
            System.out.println(s);

        }
        System.out.println(list.toString());
    }
}
