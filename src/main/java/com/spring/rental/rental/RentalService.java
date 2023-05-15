package com.spring.rental.rental;

import com.spring.rental.client.Client;
import com.spring.rental.client.ClientRepository;
import com.spring.rental.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentalService {

    private final RentalRepository rentalRepository;
    private final ClientRepository clientRepository;

    @Autowired
    public RentalService(RentalRepository rentalRepository, ClientRepository clientRepository) {
        this.rentalRepository = rentalRepository;
        this.clientRepository = clientRepository;
    }

    public List<Rental> getAllRentals() {
        return rentalRepository.findAll();
    }

    public Rental getRentalById(Long id) {
        return rentalRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("rental", id));
    }

    public Rental createRental(RentalDTO rentalDTO) {
        Client renter = clientRepository.findById(rentalDTO.getRenterId()).orElseThrow(
                () -> new NotFoundException("client", rentalDTO.getRenterId())
        );
        Client rentee = clientRepository.findById(rentalDTO.getRenteeId()).orElseThrow(
                () -> new NotFoundException("client", rentalDTO.getRenteeId())
        );
        Rental rental = new Rental(
                renter,
                rentee,
                rentalDTO.getItemId(),
                rentalDTO.getItemPriceId(),
                rentalDTO.getRenterAddressId(),
                rentalDTO.getRenteeAddressId(),
                rentalDTO.getStatus(),
                rentalDTO.getDeclaredEndDate()
        );
        return rentalRepository.save(rental);
    }

    public Rental updateRental(Long id, Rental updatedRental) {
        Rental currentRental = rentalRepository.findById(id).orElseThrow(() -> new NotFoundException("rental", id));
        updatedRental.setId(id);
        updatedRental.setRentee(currentRental.getRentee());
        updatedRental.setRenter(currentRental.getRenter());
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
