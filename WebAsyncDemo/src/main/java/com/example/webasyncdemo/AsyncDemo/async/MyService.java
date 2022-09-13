package com.example.webasyncdemo.AsyncDemo.async;

import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class MyService {

    @Autowired
    private ApplicationContext applicationContext;

    @Async
    public void testSyncTask() throws InterruptedException {
        Thread.sleep(10000);
        System.out.println("异步任务执行完成！");
    }


    public void asyncCallTwo() throws InterruptedException {
        //this.testSyncTask();
//        MyService myservice = (MyService)applicationContext.getBean(MyService.class);
//        myservice.testSyncTask();
        boolean isAop = AopUtils.isAopProxy(CallController.class);//是否是代理对象；
        boolean isCglib = AopUtils.isCglibProxy(CallController.class);  //是否是CGLIB方式的代理对象；
        boolean isJdk = AopUtils.isJdkDynamicProxy(CallController.class);  //是否是JDK动态代理方式的代理对象；
        //以下才是重点!!!
        //MyService myservice = (MyService)applicationContext.getBean(MyService.class);
        //MyService proxy = (MyService) AopContext.currentProxy(); //未使用transactional 所以取不到proxy
        //System.out.println(myservice == proxy ? true : false);
        //proxy.testSyncTask();
        System.out.println("end!!!");
    }
}
