package com.lanchonete;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class LanchoneteApplication {

    public static void main(String[] args) {
        SpringApplication.run(LanchoneteApplication.class, args);
    }

}
