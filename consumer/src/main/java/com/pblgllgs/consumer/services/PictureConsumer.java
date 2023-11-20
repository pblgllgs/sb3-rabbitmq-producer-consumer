package com.pblgllgs.consumer.services;
/*
 *
 * @author pblgl
 * Created on 20-11-2023
 *
 */

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pblgllgs.consumer.models.Picture;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PictureConsumer {

    private final ObjectMapper objectMapper;
    @RabbitListener(queues = {"q.picture.image", "q.picture.vector", "q.picture.log", "q.picture.filter"})
    public void consumeMessage(Message message) throws JsonProcessingException {
        String string = new String(message.getBody());
        Picture picture = objectMapper.readValue(string, Picture.class);
        log.info( picture.toString() + " from: " + message.getMessageProperties().getReceivedRoutingKey());
    }

}
