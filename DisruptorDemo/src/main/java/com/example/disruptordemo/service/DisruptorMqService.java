package com.example.disruptordemo.service;

public interface DisruptorMqService {
    /**
     * 消息
     * @param message
     */
    void sayHelloMq(String message);
}
