package com.pblgllgs.producer.service;
/*
 *
 * @author pblgl
 * Created on 20-11-2023
 *
 */

import com.pblgllgs.producer.models.Picture;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PictureProducer {
    private final RabbitTemplate rabbitTemplate;

    @Value("${x.picture}")
    private String exchange;

    public void sendMessage(@Payload Picture picture) {
        rabbitTemplate.convertAndSend(exchange, picture.getType(), picture);
    }

}
