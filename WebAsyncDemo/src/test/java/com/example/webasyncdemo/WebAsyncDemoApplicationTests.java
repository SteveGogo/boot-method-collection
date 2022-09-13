package com.example.webasyncdemo;

import com.example.webasyncdemo.AsyncDemo.MyAsyncTask;
import com.example.webasyncdemo.AsyncDemo.MyFutureTask;
import com.example.webasyncdemo.AsyncDemo.MyTask;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.Future;

@SpringBootTest
class WebAsyncDemoApplicationTests {

    @Autowired
    private MyTask task;

    /**
     * 并行任务测试
     */
    @Test
    public void test() throws Exception {
        task.doTaskOne();
        task.doTaskTwo();
        task.doTaskThree();
    }


    @Autowired
    private MyAsyncTask asyncTask;

    @Test
    public void test2() throws Exception {
        asyncTask.doTaskOne();
        asyncTask.doTaskTwo();
        asyncTask.doTaskThree();
    }

    @Autowired
    private MyFutureTask futureTask;

    @Test
    public void test3() throws Exception {
        long start = System.currentTimeMillis();

        Future<String> task1 = futureTask.doTaskOne();
        Future<String> task2 = futureTask.doTaskTwo();
        Future<String> task3 = futureTask.doTaskThree();

        while (true) {
            if (task1.isDone() && task2.isDone() && task3.isDone()) {
                // 三个任务都调用完成，退出循环等待
                break;
            }
            Thread.sleep(1000);

        }

        long end = System.currentTimeMillis();

        System.out.println("任务全部完成，总耗时：" + (end - start) + "毫秒");
    }


}
