package com.pblgllgs.producer.producer;
/*
 *
 * @author pblgl
 * Created on 20-11-2023
 *
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class RabbitMQConfig {

    public static final String COLOR = "color";
    public static final String MATERIAL = "material";

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

    @Bean
    public Binding bindingHeadersDiscount1(Queue newQueueDiscount, HeadersExchange exchangeHeaders) {
        Map<String, Object> headers = new HashMap<>();
        headers.put(COLOR, "white");
        headers.put(MATERIAL, "wood");

        return BindingBuilder.bind(newQueueDiscount)
                .to(exchangeHeaders)
                .whereAll(headers)
                .match();
    }

    @Bean
    public Binding bindingHeadersDiscount2(Queue newQueueDiscount, HeadersExchange exchangeHeaders) {
        Map<String, Object> headers = new HashMap<>();
        headers.put(COLOR, "red");
        headers.put(MATERIAL, "steel");

        return BindingBuilder.bind(newQueueDiscount)
                .to(exchangeHeaders)
                .whereAll(headers)
                .match();
    }

    @Bean
    public Binding bindingHeadersFree(Queue newQueueFree, HeadersExchange exchangeHeaders) {
        Map<String, Object> headers = new HashMap<>();
        headers.put(COLOR, "red");
        headers.put(MATERIAL, "wood");

        return BindingBuilder.bind(newQueueFree)
                .to(exchangeHeaders)
                .whereAny(headers)
                .match();
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
