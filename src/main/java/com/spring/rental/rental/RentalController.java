package com.spring.rental.rental;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rentals")
public class RentalController {

    private final RentalService rentalService;

    @Autowired
    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @GetMapping
    public ResponseEntity<List<Rental>> getAllRentals() {
        List<Rental> rentals = rentalService.getAllRentals();
        return ResponseEntity.ok(rentals);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rental> getRentalById(@PathVariable Long id) {
        Rental rental = rentalService.getRentalById(id);
        return ResponseEntity.ok(rental);
    }

    @PostMapping
    public ResponseEntity<Rental> createRental(@RequestBody Rental rental) {
        Rental savedRental = rentalService.createRental(rental);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRental);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Rental> updateRental(@PathVariable Long id, @RequestBody Rental updatedRental) {
        Rental savedRental = rentalService.updateRental(id, updatedRental);
        return ResponseEntity.ok(savedRental);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRental(@PathVariable Long id) {
        rentalService.deleteRental(id);
        return ResponseEntity.noContent().build();
    }
}


