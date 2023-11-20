package com.pblgllgs.producer.service;
/*
 *
 * @author pblgl
 * Created on 20-11-2023
 *
 */

import com.pblgllgs.producer.models.Picture;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PictureProducer {
    private final RabbitTemplate rabbitTemplate;

    @Value("${x.picture}")
    private String exchange;

    public void sendMessage(Picture picture) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(picture.getSource()).append(".");
        if (picture.getSize() > 4000) {
            stringBuilder.append("large");
        } else {
            stringBuilder.append("small");
        }
        stringBuilder.append(".").append(picture.getType());
        rabbitTemplate.convertAndSend(exchange, stringBuilder.toString(), picture);
    }

}
