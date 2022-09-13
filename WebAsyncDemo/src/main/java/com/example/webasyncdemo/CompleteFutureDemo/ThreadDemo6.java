package com.example.webasyncdemo.CompleteFutureDemo;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 任何一个任务处理完成就返回
 * anyOf()
 */
public class ThreadDemo6 {
    public static void main(String[] args) {
        // 1. 创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        List<Integer> list = Arrays.asList(1, 2, 3);
        long start = System.currentTimeMillis();
        // 2. 提交任务
        CompletableFuture<Object> completableFuture = CompletableFuture.anyOf(list.stream().map(key -> CompletableFuture.supplyAsync(() -> {
            // 睡眠一秒，模仿处理过程
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
            }
            return "结果" + key;
        }, executorService)).toArray(CompletableFuture[]::new));
        executorService.shutdown();

        // 3. 获取结果
        System.out.println(completableFuture.join());
    }
}
