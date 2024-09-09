package com.eindopdrachtbackend;  // Ensure this matches your project structure

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;


@SpringBootApplication
@EnableJpaRepositories("com.eindopdrachtbackend.repository") // Specify the repository package
@EntityScan("com.eindopdrachtbackend.model") // Specify the entity package
public class EindopdrachtBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(EindopdrachtBackendApplication.class, args);
    }
}
