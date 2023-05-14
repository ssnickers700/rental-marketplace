package com.spring.rental.rental;

import com.spring.rental.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentalService {

    private final RentalRepository rentalRepository;

    @Autowired
    public RentalService(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    public List<Rental> getAllRentals() {
        return rentalRepository.findAll();
    }

    public Rental getRentalById(Long id) {
        return rentalRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("rental", id));
    }

    public Rental createRental(Rental rental) {
        return rentalRepository.save(rental);
    }

    public Rental updateRental(Long id, Rental updatedRental) {
        if (!rentalRepository.existsById(id)) {
            throw new NotFoundException("rental", id);
        }
        updatedRental.setId(id);
        rentalRepository.save(updatedRental);
        return updatedRental;
    }

    public void deleteRental(Long id) {
        if (!rentalRepository.existsById(id)) {
            throw new NotFoundException("rental", id);
        }
        rentalRepository.deleteById(id);
    }
}
