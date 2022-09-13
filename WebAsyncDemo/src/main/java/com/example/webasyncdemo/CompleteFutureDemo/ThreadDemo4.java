package com.example.webasyncdemo.CompleteFutureDemo;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 等待所有任务线程执行完成
 * 线程池和CountDownLatch使用
 * 用法过时~!
 */
public class ThreadDemo4 {

    public static void main(String[] args) {
        // 1. 创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        List<Integer> list = Arrays.asList(1, 2, 3);
        CountDownLatch countDownLatch = new CountDownLatch(list.size());
        for (Integer key : list) {
            // 2. 提交任务
            executorService.execute(() -> {
                // 睡眠一秒，模仿处理过程
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                }
                System.out.println("结果" + key);
                countDownLatch.countDown();
            });
        }

        executorService.shutdown();
        // 3. 阻塞等待所有任务执行完成
        try {
            countDownLatch.await();
            System.out.println("所有线程执行完成~");
        } catch (InterruptedException e) {
        }
    }

}
