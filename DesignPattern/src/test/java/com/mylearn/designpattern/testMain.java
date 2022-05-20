package com.mylearn.designpattern;

import com.mylearn.designpattern.EventBus.EventBusCenter;
import com.mylearn.designpattern.EventBus.EventListener;
import com.mylearn.designpattern.EventBus.NotifyEvent;

public class testMain {
    public static void main(String[] args) {

        EventListener eventListener = new EventListener();
        EventBusCenter.register(eventListener);
        EventBusCenter.post(new NotifyEvent("13372817283", "123@qq.com", "666"));
    }
}
