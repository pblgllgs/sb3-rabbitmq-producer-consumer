package com.pblgllgs.producer.service;
/*
 *
 * @author pblgl
 * Created on 20-11-2023
 *
 */

import com.pblgllgs.producer.models.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HumanResourceProducer {

    private final RabbitTemplate rabbitTemplate;

    public void sendMessageWithEmployee(Employee employee) {
        rabbitTemplate.convertAndSend("x.hr", "", employee);
    }

}
