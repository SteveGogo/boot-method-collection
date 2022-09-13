package com.example.webasyncdemo.CompleteFutureDemo;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * 使用completeFuture,并对结果进行聚合
 */
public class ThreadDemo3 {
    public static void main(String[] args) {
        // 1. 创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        List<Integer> list = Arrays.asList(1, 2, 3);
        // 2. 提交任务
        List<String> results = list.stream().map(key ->
                        CompletableFuture.supplyAsync(() -> {
                            // 睡眠一秒，模仿处理过程
                            try {
                                Thread.sleep(1000L);
                            } catch (InterruptedException e) {
                            }
                            return "结果" + key;
                        }, executorService))
                //聚合
                .map(CompletableFuture::join).collect(Collectors.toList());

        executorService.shutdown();
        // 3. 获取结果
        System.out.println(results);

    }
}
