package com.example.webasyncdemo.CompleteFutureDemo;

import org.apache.commons.lang3.RandomUtils;

import java.util.concurrent.CompletableFuture;

/**
 * complete方法使用示例
 */
public class ThreadDemo9 {


    public static void main(String[] args) {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            return "饭做好了";
        });

        //try {
        //    Thread.sleep(1L);
        //} catch (InterruptedException e) {
        //}

        completableFuture.complete("饭还没做好，我点外卖了");
        System.out.println(completableFuture.join());
    }


}
