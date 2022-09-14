package com.example.rabbitmqdemo.Provider.sender;

public interface IMessageServcie {
    public void sendMessage(String exchange, String routingKey, Object msg);
}



