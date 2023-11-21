package com.pblgllgs.consumer.consumer;
/*
 *
 * @author pblgl
 * Created on 20-11-2023
 *
 */

import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${q.promotion.discount}")
    private String queueDiscount;

    @Value("${q.promotion.free-delivery}")
    private String queueFree;


    @Value("${x.promotion}")
    private String exchange;

    @Bean
    public Queue newQueueDiscount() {
        return new Queue(queueDiscount);
    }

    @Bean
    public Queue newQueueFree() {
        return new Queue(queueFree);
    }

    @Bean
    public HeadersExchange exchangeHeaders() {
        return new HeadersExchange(exchange);
    }

}
