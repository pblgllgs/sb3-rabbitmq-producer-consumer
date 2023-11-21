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

    @Value("${x.picture}")
    private String exchange;

    @Value("${routing-key.jpg}")
    private String jpg;

    @Value("${routing-key.png}")
    private String png;

    @Value("${routing-key.svg}")
    private String svg;


    @Bean
    public Queue newQueueImage(){
        return new Queue(queueImage);
    }
    @Bean
    public Queue newQueueVector(){
        return new Queue(queueVector);
    }

    @Bean
    public DirectExchange exchangeDirect(){
        return new DirectExchange(exchange);
    }

    @Bean
    public Binding bindingDirectJPG(Queue newQueueImage, DirectExchange exchangeDirect) {
        return BindingBuilder.bind(newQueueImage).to(exchangeDirect).with(jpg);
    }

    @Bean
    public Binding bindingDirectPNG(Queue newQueueImage, DirectExchange exchangeDirect) {
        return BindingBuilder.bind(newQueueImage).to(exchangeDirect).with(png);
    }

    @Bean
    public Binding bindingDirectSVG(Queue newQueueVector, DirectExchange exchangeDirect) {
        return BindingBuilder.bind(newQueueVector).to(exchangeDirect).with(svg);
    }


    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return new Jackson2JsonMessageConverter(objectMapper);
    }
}
