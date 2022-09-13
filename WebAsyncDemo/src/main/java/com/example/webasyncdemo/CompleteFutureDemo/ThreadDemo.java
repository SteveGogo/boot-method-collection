package com.example.webasyncdemo.CompleteFutureDemo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Future的简单应用
 */
public class ThreadDemo {
    public static void main(String[] args) {
        // 1. 创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        List<Integer> list = Arrays.asList(1, 2, 3);
        List<Future<String>> futures = new ArrayList<>();
        for (Integer key : list) {
            // 2. 提交任务
            Future<String> future = executorService.submit(() -> {
                // 睡眠一秒，模仿处理过程
                Thread.sleep(2000L);
                return "结果" + key;
            });
            futures.add(future);
        }

        // 3. 获取结果
        for (Future<String> future : futures) {
            try {
                //future.get() 会阻塞当前线程
                String result = future.get();
                System.out.println(result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        executorService.shutdown();
    }
}
