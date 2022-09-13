package com.example.webasyncdemo.CompleteFutureDemo;

import java.util.concurrent.CompletableFuture;

/**
 * either用法示例
 */
public class ThreadDemo10 {


    public static void main(String[] args) {
        CompletableFuture<String> meal = CompletableFuture.supplyAsync(() -> {
            return "饭做好了";
        });
        CompletableFuture<String> outMeal = CompletableFuture.supplyAsync(() -> {
            return "外卖到了";
        });

        // 饭先做好，就吃饭。外卖先到，就吃外卖。就是这么任性。
        CompletableFuture<String> completableFuture = meal.applyToEither(outMeal, myMeal -> {
            return myMeal;
        });
        System.out.println(completableFuture.join());
    }
}
