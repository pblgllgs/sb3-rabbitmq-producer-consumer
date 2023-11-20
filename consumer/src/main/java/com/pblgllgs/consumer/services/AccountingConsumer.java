package com.pblgllgs.consumer.services;
/*
 *
 * @author pblgl
 * Created on 20-11-2023
 *
 */

import com.pblgllgs.consumer.models.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AccountingConsumer {

    @RabbitListener(queues = "q.hr.accounting")
    public void consumeMessageAccounting(@Payload Employee employee){
        log.info(employee.toString() + " from: q.hr.accounting");
    }
}
