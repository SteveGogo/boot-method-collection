package com.mylearn.designpattern.EventBus;

import com.google.common.eventbus.EventBus;

/**
 * 基于 Guava EventBus 提供一套基于注解的事件总线，api可以灵活的使用
 */
public class EventBusCenter {
    private static EventBus eventBus = new EventBus();

    private EventBusCenter() {
    }

    public static EventBus getInstance() {
        return eventBus;
    }
    //添加观察者
    public static void register(Object obj) {
        eventBus.register(obj);
    }
    //移除观察者
    public static void unregister(Object obj) {
        eventBus.unregister(obj);
    }
    //把消息推给观察者
    public static void post(Object obj) {
        eventBus.post(obj);
    }
}



