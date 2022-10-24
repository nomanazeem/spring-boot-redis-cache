package com.rediscache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


@SpringBootApplication
@EnableCaching
@Slf4j
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);

        log.info("Hello world! spring boot redis cache");
    }
}