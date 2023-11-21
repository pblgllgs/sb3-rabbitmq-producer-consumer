package com.pblgllgs.producer.producer;
/*
 *
 * @author pblgl
 * Created on 20-11-2023
 *
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${q.hr.marketing}")
    private String queueMarketing;

    @Value("${q.hr.accounting}")
    private String queueAccounting;

    @Value("${x.hr}")
    private String exchange;


    @Bean
    public Queue newQueueMarketing(){
        return new Queue(queueMarketing);
    }
    @Bean
    public Queue newQueueAccounting(){
        return new Queue(queueAccounting);
    }

    @Bean
    public FanoutExchange exchangeFanout(){
        return new FanoutExchange(exchange);
    }

    @Bean
    public Binding bindingFanoutMarketing(Queue newQueueMarketing, FanoutExchange exchangeFanout) {
        return BindingBuilder.bind(newQueueMarketing).to(exchangeFanout);
    }

    @Bean
    public Binding bindingDirect(Queue newQueueAccounting, FanoutExchange exchange) {
        return BindingBuilder.bind(newQueueAccounting).to(exchange);
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return new Jackson2JsonMessageConverter(objectMapper);
    }

}
