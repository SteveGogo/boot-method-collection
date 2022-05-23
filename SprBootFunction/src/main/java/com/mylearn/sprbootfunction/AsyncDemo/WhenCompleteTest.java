package com.mylearn.sprbootfunction.AsyncDemo;

import java.util.concurrent.CompletableFuture;

public class WhenCompleteTest {
    public static void main(String[] args) {
        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> "hello");
        //对前面计算结果进行处理 whenComplete
        CompletableFuture<String> cf2 = cf1.whenComplete((v, e) ->
                System.out.println(String.format("value:%s, exception:%s", v, e)));
        System.out.println(cf2.join());
    }





}
