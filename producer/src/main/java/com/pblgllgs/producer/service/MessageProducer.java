package com.pblgllgs.producer.service;
/*
 *
 * @author pblgl
 * Created on 20-11-2023
 *
 */

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MessageProducer {

    private final RabbitTemplate rabbitTemplate;

    private int i = 0;

//    @Scheduled(fixedRate = 500)
//    public void sendMessage(){
//        i ++;
//        log.info(String.valueOf(i));
//        rabbitTemplate.convertAndSend(queue,i);
//    }

}
