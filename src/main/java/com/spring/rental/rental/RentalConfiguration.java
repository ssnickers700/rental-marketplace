package com.spring.rental.rental;

import com.spring.rental.client.ClientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class RentalConfiguration {

    @Bean
    CommandLineRunner commandLineRunnerRental(RentalRepository rentalRepository, ClientRepository clientRepository) {
        return args -> rentalRepository.save(new Rental(
                clientRepository.findById(1L).orElseThrow(),
                clientRepository.findById(1L).orElseThrow(),
                1L, 1L, 1L, 2L, Status.IN_PREPARATION,
                LocalDateTime.of(2023, 6, 20, 8, 22)
        ));

    }
}
