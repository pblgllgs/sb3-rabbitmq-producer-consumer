package com.pblgllgs.producer.service;
/*
 *
 * @author pblgl
 * Created on 20-11-2023
 *
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pblgllgs.producer.models.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeProducer {

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper mapper;

    public void sendMessageWithEmployee(Employee employee) {
        rabbitTemplate.convertAndSend("employee", employee);
    }

}
