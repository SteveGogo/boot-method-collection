package com.mylearn.sprbootfunction.AsyncDemo;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class ThenCombineTest {

    //thenCombine方法将两个无关的CompletableFuture组合起来
    private static Random random = new Random();
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> result = CompletableFuture.supplyAsync(ThenCombineTest::randomInteger).thenCombine(
                CompletableFuture.supplyAsync(ThenCombineTest::randomInteger), (i, j) -> i * j);

        System.out.println(result.get());
    }

    public static Integer randomInteger() {
        return random.nextInt(100);
    }

}
