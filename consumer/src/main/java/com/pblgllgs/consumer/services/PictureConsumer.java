package com.pblgllgs.consumer.services;
/*
 *
 * @author pblgl
 * Created on 20-11-2023
 *
 */

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pblgllgs.consumer.models.Furniture;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PictureConsumer {

    private final ObjectMapper objectMapper;

    @RabbitListener(queues = {"q.promotion.discount", "q.promotion.free-delivery"})
    public void consumeMessage(Message message) throws JsonProcessingException {
        String string = new String(message.getBody());
        Furniture furniture = objectMapper.readValue(string, Furniture.class);
        log.info(furniture.toString() + " - " + message.getMessageProperties().getHeaders());
    }

}
