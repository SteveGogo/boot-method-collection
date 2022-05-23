package com.mylearn.sprbootfunction.AsyncDemo;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ThenAcceptTest {
    public static void main(String[] args) {
        CompletableFuture.supplyAsync(ThenAcceptTest::getList).thenAccept(strList -> strList.stream()
                .forEach(m -> System.out.println(m)));
    }

    public static List<String> getList() {
        return Arrays.asList("a", "b", "c");
    }
}
