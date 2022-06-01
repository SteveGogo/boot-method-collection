package com.example.disruptordemo.dispurtor;

import com.lmax.disruptor.EventFactory;

/**
 * 事件实体工厂
 */
public class HelloEventFactory implements EventFactory<MessageModel> {
    @Override
    public MessageModel newInstance() {
        return new MessageModel();
    }
}
