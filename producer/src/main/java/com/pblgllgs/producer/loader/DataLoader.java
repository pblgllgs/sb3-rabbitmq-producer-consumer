package com.pblgllgs.producer.loader;
/*
 *
 * @author pblgl
 * Created on 20-11-2023
 *
 */

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pblgllgs.producer.models.Furniture;
import com.pblgllgs.producer.service.FurnitureProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataLoader implements CommandLineRunner {

    private final FurnitureProducer furnitureProducer;

    private final List<String> COLORS = List.of("white", "red", "green");
    private final List<String> MATERIAL = List.of("wood", "steel", "plastic");

    @Override
    public void run(String... args) throws JsonProcessingException {
        for (int i = 0; i < 10; i++) {
            Furniture furniture = Furniture.builder()
                    .name("Furniture: " + i)
                    .color(COLORS.get(i % COLORS.size()))
                    .material(MATERIAL.get(i % MATERIAL.size()))
                    .price(i)
                    .build();
            furnitureProducer.sendMessage(furniture);
        }

    }
}
