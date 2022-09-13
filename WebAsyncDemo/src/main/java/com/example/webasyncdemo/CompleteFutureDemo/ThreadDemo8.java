package com.example.webasyncdemo.CompleteFutureDemo;

import org.apache.commons.lang3.RandomUtils;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * then、handle方法使用示例
 */
public class ThreadDemo8 {

    public static void main(String[] args) {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("1. 开始淘米");
            return "2. 淘米完成";
        }).thenApplyAsync(result -> {
            System.out.println(result);
            System.out.println("3. 开始煮饭");
            // 生成一个1~10的随机数
            if (RandomUtils.nextInt(1, 10) > 5) {
                throw new RuntimeException("4. 电饭煲坏了，煮不了");
            }
            return "4. 煮饭完成";
        }).handleAsync((result, exception) -> {
            if (exception != null) {
                System.out.println(exception.getMessage());
                return "5. 今天没饭吃";
            } else {
                System.out.println(result);
                return "5. 开始吃饭";
            }
        });

        try {
            String result = completableFuture.get();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
