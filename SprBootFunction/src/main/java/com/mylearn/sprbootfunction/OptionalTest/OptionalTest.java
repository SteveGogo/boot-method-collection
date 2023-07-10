package com.mylearn.sprbootfunction.OptionalTest;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

public class OptionalTest {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
//        list.add("a");
//        list.add("b");
//        list.add("c");
//        list.add("d");

        Lists.partition(list, 3).get(0).forEach(System.out::println);
    }
}
