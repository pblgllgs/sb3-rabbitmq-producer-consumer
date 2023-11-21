package com.pblgllgs.producer.service;
/*
 *
 * @author pblgl
 * Created on 20-11-2023
 *
 */

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pblgllgs.producer.models.Furniture;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class FurnitureProducer {
    private final RabbitTemplate rabbitTemplate;

    @Value("${x.promotion}")
    private String exchange;

    private final ObjectMapper objectMapper;

    public void sendMessage(Furniture furniture) throws JsonProcessingException {
        MessageProperties messageProperties = new MessageProperties();
        log.info(furniture.toString());
        messageProperties.setHeader("color",furniture.getColor());
        messageProperties.setHeader("material",furniture.getMaterial());
        String valueAsString = objectMapper.writeValueAsString(furniture);
        log.info(valueAsString);
        Message message = new Message(valueAsString.getBytes(), messageProperties);
        rabbitTemplate.send(exchange,"",message);
    }

}
