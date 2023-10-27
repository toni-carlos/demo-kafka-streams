package com.demo;

import com.demo.streams.KafkaStreamer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;


@SpringBootApplication
class Application implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    @Autowired
    private KafkaStreamer kafkaStreamer;

    public static void main(String[] args) throws IOException {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        LOGGER.info("------------------------------------ Application Started !! ----------------------------------");
        kafkaStreamer.start();
        LOGGER.info("------------------------------------ Application Finished !! ---------------------------------");
    }
    //org.apache.kafka.common.serialization.
}

