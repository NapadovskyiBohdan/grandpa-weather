package com.grandpaweather;

import io.mongock.runner.springboot.EnableMongock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
@EnableMongock
public class GrandpaWeather {

    private static final Logger log = LoggerFactory.getLogger(GrandpaWeather.class);

    public static void main(String[] args) {
        SpringApplication.run(GrandpaWeather.class, args);
    }

}
