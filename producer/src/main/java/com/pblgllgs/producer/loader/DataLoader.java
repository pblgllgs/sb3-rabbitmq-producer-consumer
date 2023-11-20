package com.pblgllgs.producer.loader;
/*
 *
 * @author pblgl
 * Created on 20-11-2023
 *
 */

import com.pblgllgs.producer.models.Employee;
import com.pblgllgs.producer.service.HumanResourceProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataLoader implements CommandLineRunner {

    private final HumanResourceProducer humanResourceProducer;

    @Override
    public void run(String... args) throws Exception {
        Employee employee = new Employee(UUID.randomUUID().toString(), "pblgllgs", LocalDate.now());
        log.info(employee.toString());
        humanResourceProducer.sendMessageWithEmployee(employee);
    }
}
