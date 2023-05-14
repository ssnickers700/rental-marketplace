package com.spring.rental.client;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ClientConfiguration {

    @Bean
    CommandLineRunner commandLineRunnerClient(ClientRepository clientRepository) {
        return args -> clientRepository.saveAll(List.of(
                        new Client("first@gmail.com", "pass123", "One", "Filly", 123123123, Role.REGULAR),
                        new Client("second@gmail.com", "123pass", "Eoo", "Bee", 321321321, Role.REGULAR)
                )
        );

    }
}
