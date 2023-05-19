package com.spring.rental;

import com.spring.rental.address.Address;
import com.spring.rental.address.AddressRepository;
import com.spring.rental.category.Category;
import com.spring.rental.category.CategoryRepository;
import com.spring.rental.client.Client;
import com.spring.rental.client.ClientRepository;
import com.spring.rental.client.Role;
import com.spring.rental.item.Item;
import com.spring.rental.item.ItemRepository;
import com.spring.rental.item.State;
import com.spring.rental.rental.Rental;
import com.spring.rental.rental.RentalRepository;
import com.spring.rental.rental.Status;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Configuration
public class DatabaseInitializer {

    @Bean
    CommandLineRunner commandLineRunnerClient(ClientRepository clientRepository) {
        return args -> clientRepository.saveAll(List.of(
                        new Client("first@gmail.com", "pass123", "One", "Filly", 123123123, Role.REGULAR),
                        new Client("second@gmail.com", "123pass", "Eoo", "Bee", 321321321, Role.REGULAR)
                )
        );
    }

    @Bean
    CommandLineRunner commandLineRunnerAddress(AddressRepository addressRepository, ClientRepository clientRepository) {
        return args -> addressRepository.saveAll(List.of(
                        new Address(clientRepository.findById(1L).orElseThrow(),"Maverick Ave 32", "Philly", "11230", "USA"),
                        new Address(clientRepository.findById(2L).orElseThrow(), "Boony St 11", "Ohio", "55430", "USA"),
                        new Address(clientRepository.findById(2L).orElseThrow(), "Mace's St 5", "Ohio", "55889", "USA")
                )
        );
    }

    @Bean
    CommandLineRunner commandLineRunnerCategory(CategoryRepository categoryRepository) {
        return args -> categoryRepository.saveAll(List.of(
                        new Category("Gaming"),
                        new Category("Garden"),
                        new Category("Coffee machines"),
                        new Category("Cameras"),
                        new Category("Music")
                )
        );
    }

    @Bean
    CommandLineRunner commandLineRunnerItem(ItemRepository itemRepository, ClientRepository clientRepository, CategoryRepository categoryRepository) {
        return args -> itemRepository.saveAll(List.of(
                        new Item(clientRepository.findById(1L).orElseThrow(), categoryRepository.findById(1L).orElseThrow(), "Playstation 5", "1tb ssd, physical drive", State.ACTIVE, BigDecimal.valueOf(700)),
                        new Item(clientRepository.findById(2L).orElseThrow(), categoryRepository.findById(2L).orElseThrow(), "Lawnmower Toro", "Got it 2 years ago and simply prefer scissors", State.ACTIVE, BigDecimal.valueOf(500)),
                        new Item(clientRepository.findById(2L).orElseThrow(), categoryRepository.findById(3L).orElseThrow(), "De'longhi coffee machine", "milk frother not working", State.INACTIVE, BigDecimal.valueOf(1000))
                )
        );
    }

    @Bean
    CommandLineRunner commandLineRunnerRental(RentalRepository rentalRepository, ClientRepository clientRepository, AddressRepository addressRepository, ItemRepository itemRepository) {
        return args -> rentalRepository.save(new Rental(
                clientRepository.findById(1L).orElseThrow(),
                clientRepository.findById(2L).orElseThrow(),
                itemRepository.findById(1L).orElseThrow(),
                1L,
                addressRepository.findById(1L).orElseThrow(),
                addressRepository.findById(2L).orElseThrow(),
                Status.IN_PREPARATION,
                LocalDateTime.of(2023, 6, 20, 8, 22)
        ));
    }
}

