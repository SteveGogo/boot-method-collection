package com.example.nettysocketiodemo.config;


import org.springframework.beans.factory.InitializingBean;

import java.util.Map;
import java.util.Observer;


public interface TestSocketIOService  extends Observer, InitializingBean {

    /**
     * description: 给容器内所有的客户端发送通知
     * date: 2021年-09月-09日 14:08
     * author: zhanghang
     *
     * @param msg
     * @return void
     */
    void sendMessageToAllUser(Map<String,Object> msg);

    /**
     * description: 给指定用户发送通知
     * date: 2021年-09月-09日 14:09
     * author: zhanghang
     *
     * @param username
     * @param msg
     * @return void
     */
    void sendMessage(String username, Map<String,Object> msg);
}


