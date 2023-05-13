package com.spring.rental.client;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientConfiguration {

    @Bean
    CommandLineRunner commandLineRunner(ClientRepository clientRepository) {
        return args -> clientRepository.save(new Client("ee@ee.ee", "pass123", "Eoo", "Bee", 123123123, Role.REGULAR));

    }
}
