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
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class MessageConsumer {

//    @RabbitListener(queues = "my.queue",concurrency = "3-7")
//    public void consumerMessage(String message) throws InterruptedException {
//        TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextLong(1000,2000));
//        log.info("{} : Consuming {}" , Thread.currentThread().getName(), message);
//    }


    @RabbitListener(queues = "employee")
    public void consumeMessage(Employee employee){
        log.info(employee.toString());
    }
}
