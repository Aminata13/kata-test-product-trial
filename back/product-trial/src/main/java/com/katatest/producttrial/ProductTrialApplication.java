package com.katatest.producttrial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class ProductTrialApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductTrialApplication.class, args);
    }
}
