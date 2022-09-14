package com.example.rabbitmqdemo.Provider.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 直连交换机，发送指定队列信息，但这个队列后有两个消费者同时进行消费
 *
 * @author 7651
 */
@Configuration
public class DirectExchangeTxQueueConfig {

    /**
     * exchange
     */
    @Bean(name = "getDirectExchangeTx")
    public DirectExchange getDirectExchangeTx() {
        return new DirectExchange("directExchangeTx", false, false);
    }

    /**
     * queue
     */
    @Bean(name = "getQueueTx")
    public Queue getQueueTx() {
        return new Queue("directQueueTx", true, false, false);
    }

    /**
     * 绑定Q和exchange
     */
    @Bean
    public Binding getDirectExchangeQueueTx(
            @Qualifier(value = "getDirectExchangeTx") DirectExchange getDirectExchangeTx,
            @Qualifier(value = "getQueueTx") Queue getQueueTx) {
        return BindingBuilder.bind(getQueueTx).to(getDirectExchangeTx).with("directQueueTxRoutingKey");
    }
}
