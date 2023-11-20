package com.pblgllgs.consumer.services;
/*
 *
 * @author pblgl
 * Created on 20-11-2023
 *
 */

import com.pblgllgs.consumer.models.Picture;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PictureConsumer {

    @Value("${q.picture.vector}")
    private String queueVector;

    @Value("${q.picture.image}")
    private String queueImage;

    @RabbitListener(queues = "q.picture.image")
    public void consumeMessage(@Payload Picture picture){
        log.info(picture.toString() + " from: " + queueImage);
    }

    @RabbitListener(queues = "q.picture.vector")
    public void consumeMessageMarketing(@Payload Picture picture){
        log.info(picture.toString() + " from: "+ queueVector);
    }

}
