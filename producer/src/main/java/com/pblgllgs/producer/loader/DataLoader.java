package com.pblgllgs.producer.loader;
/*
 *
 * @author pblgl
 * Created on 20-11-2023
 *
 */

import com.pblgllgs.producer.models.Picture;
import com.pblgllgs.producer.service.PictureProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataLoader implements CommandLineRunner {

    private final PictureProducer pictureProducer;

    private final List<String> SOURCES = List.of("mobile", "web");
    private final List<String> TYPES = List.of("jpg", "png", "svg");

    @Override
    public void run(String... args) {
        for (int i = 0; i < 10; i++) {
            Picture picture = Picture.builder()
                    .name("Picture - " + UUID.randomUUID())
                    .size(ThreadLocalRandom.current().nextLong(1, 10000))
                    .source(SOURCES.get(i % SOURCES.size()))
                    .type(TYPES.get(i % TYPES.size()))
                    .build();
            pictureProducer.sendMessage(picture);
        }
    }
}
