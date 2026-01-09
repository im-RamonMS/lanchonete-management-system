package com.ramon.lanchonetemanagementsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class LanchoneteManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(LanchoneteManagementSystemApplication.class, args);
    }

}
