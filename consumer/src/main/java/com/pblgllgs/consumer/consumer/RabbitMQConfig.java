package com.pblgllgs.consumer.consumer;
/*
 *
 * @author pblgl
 * Created on 20-11-2023
 *
 */

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${q.picture.vector}")
    private String queueVector;

    @Value("${q.picture.image}")
    private String queueImage;

    @Value("${q.picture.log}")
    private String queueLog;

    @Value("${q.picture.filter}")
    private String queueFilter;

    @Value("${x.picture}")
    private String exchange;

    @Bean
    public Queue newQueueImage(){
        return new Queue(queueImage);
    }
    @Bean
    public Queue newQueueVector(){
        return new Queue(queueVector);
    }

    @Bean
    public Queue newQueueLog(){
        return new Queue(queueLog);
    }
    @Bean
    public Queue newQueueFilter(){
        return new Queue(queueFilter);
    }

    @Bean
    public TopicExchange exchangeTopic(){
        return new TopicExchange(exchange);
    }
}
