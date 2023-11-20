package com.pblgllgs.producer.producer;
/*
 *
 * @author pblgl
 * Created on 20-11-2023
 *
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
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

    @Value("${routing-key.jpg}")
    private String jpg;

    @Value("${routing-key.png}")
    private String png;

    @Value("${routing-key.svg}")
    private String svg;

    @Value("${routing-key.log}")
    private String log;

    @Value("${routing-key.filter}")
    private String filter;


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

    @Bean
    public Binding bindingDirectJPG(Queue newQueueImage, TopicExchange exchangeTopic) {
        return BindingBuilder.bind(newQueueImage).to(exchangeTopic).with(jpg);
    }

    @Bean
    public Binding bindingDirectPNG(Queue newQueueImage, TopicExchange exchangeTopic) {
        return BindingBuilder.bind(newQueueImage).to(exchangeTopic).with(png);
    }

    @Bean
    public Binding bindingDirectLOG(Queue newQueueLog, TopicExchange exchangeTopic) {
        return BindingBuilder.bind(newQueueLog).to(exchangeTopic).with(log);
    }

    @Bean
    public Binding bindingDirectFILTER(Queue newQueueFilter, TopicExchange exchangeTopic) {
        return BindingBuilder.bind(newQueueFilter).to(exchangeTopic).with(filter);
    }

    @Bean
    public Binding bindingDirectSVG(Queue newQueueVector, TopicExchange exchangeTopic) {
        return BindingBuilder.bind(newQueueVector).to(exchangeTopic).with(svg);
    }


    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return new Jackson2JsonMessageConverter(objectMapper);
    }

    @Bean
    public RabbitTemplate rabbitTemplateDirect(ConnectionFactory connectionFactory, Jackson2JsonMessageConverter messageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setExchange(exchange);
        rabbitTemplate.setMessageConverter(messageConverter);
        return rabbitTemplate;
    }


}
