package com.mylearn.sprbootfunction.AsyncDemo;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class FindPriceDemo {
    private static List<Shop> shopList = Arrays.asList(
            new Shop("BestPrice"),
            new Shop("LetsSaveBig"),
            new Shop("MyFavoriteShop"),
            new Shop("BuyItAll")
    );


    /**
     * 同步执行
     * @param product
     * @return
     */
    private static List<String> findPriceSync(String product) {
        return shopList.stream()
                .map(shop -> String.format("%s price is %.2f",
                        shop.getName(), shop.getPrice(product)))  //格式转换
                .collect(Collectors.toList());
    }

    /**
     * 异步执行
     * @param product
     * @return
     */
    private static List<String> findPriceAsync(String product) {
        List<CompletableFuture<String>> completableFutureList = shopList.stream()
                //转异步执行
                .map(shop -> CompletableFuture.supplyAsync(
                        () -> String.format("%s price is %.2f",
                                shop.getName(), shop.getPrice(product))))  //格式转换
                .collect(Collectors.toList());
        return completableFutureList.stream()
                .map(CompletableFuture::join)  //获取结果不会抛出异常
                .collect(Collectors.toList());
    }


    /**
     * 调用线程池的submit方法可以让任务以异步的方式运行[not ok , 应使用completable future,为后续处理提供保证]
     * @param product
     * @return
     */
    private static List<String> findPriceFutureAsync(String product) {
        //线程池的submit方法可以让任务以异步的方式运行
        ExecutorService es = Executors.newCachedThreadPool();
        List<Future<String>> futureList = shopList.stream().map(shop -> es.submit(() -> String.format("%s price is %.2f",
                shop.getName(), shop.getPrice(product)))).collect(Collectors.toList());

        return futureList.stream()
                .map(f -> {
                    String result = null;
                    try {
                        result = f.get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                    return result;
                }).collect(Collectors.toList());
    }





}
