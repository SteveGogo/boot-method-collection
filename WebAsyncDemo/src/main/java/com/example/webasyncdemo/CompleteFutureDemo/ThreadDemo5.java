package com.example.webasyncdemo.CompleteFutureDemo;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author yideng
 * @apiNote CompletableFuture.allOf()方法使用示例
 */
public class ThreadDemo5 {

    public static void main(String[] args) {
        // 1. 创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        List<Integer> list = Arrays.asList(1, 2, 3);
        // 2. 提交任务，并调用join()阻塞等待所有任务执行完成
        CompletableFuture.allOf(list.stream().map(key -> CompletableFuture.runAsync(() -> {
            // 睡眠一秒，模仿处理过程
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
            }
            System.out.println("结果" + key);
        }, executorService)).toArray(CompletableFuture[]::new)).join();
        executorService.shutdown();
    }

}