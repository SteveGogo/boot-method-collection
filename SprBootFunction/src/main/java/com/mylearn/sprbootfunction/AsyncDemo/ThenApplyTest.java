package com.mylearn.sprbootfunction.AsyncDemo;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class ThenApplyTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {


        //CompletableFuture传递给thenApply，返回thenApply处理后的结果。
        // 可以认为通过thenApply方法实现 CompletableFuture<T>至 CompletableFuture<U>的转换。
        CompletableFuture<Integer> result = CompletableFuture.supplyAsync(ThenApplyTest::randomInteger).thenApply((i) -> i * 8);
        System.out.println(result.get());
    }

    public static Integer randomInteger() {
        return 10;
    }
}
