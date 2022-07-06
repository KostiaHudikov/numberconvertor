package com.example.numberconvertor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class NumberConvertorApplication {

    public static void main(String[] args) {
        SpringApplication.run(NumberConvertorApplication.class, args);
    }

}
