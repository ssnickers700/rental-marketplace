package com.spring.rental.rental;

import com.spring.rental.address.Address;
import com.spring.rental.address.AddressRepository;
import com.spring.rental.client.Client;
import com.spring.rental.client.ClientRepository;
import com.spring.rental.exception.NotFoundException;
import com.spring.rental.item.Item;
import com.spring.rental.item.ItemRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentalService {

    private final RentalRepository rentalRepository;
    private final ClientRepository clientRepository;
    private final AddressRepository addressRepository;
    private final ItemRepository itemRepository;

    @Autowired
    public RentalService(
            RentalRepository rentalRepository,
            ClientRepository clientRepository,
            AddressRepository addressRepository,
            ItemRepository itemRepository) {
        this.rentalRepository = rentalRepository;
        this.clientRepository = clientRepository;
        this.addressRepository = addressRepository;
        this.itemRepository = itemRepository;
    }

    public List<Rental> getAllRentals() {
        return rentalRepository.findAll();
    }

    public Rental getRentalById(Long id) {
        return rentalRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("rental", id));
    }

    @Transactional
    public Rental createRental(RentalDTO rentalDTO) {
        Client renter = clientRepository
                .findById(rentalDTO.renterId())
                .orElseThrow(() -> new NotFoundException("client", rentalDTO.renterId()));

        Client rentee = clientRepository
                .findById(rentalDTO.renteeId())
                .orElseThrow(() -> new NotFoundException("client", rentalDTO.renteeId()));

        Address renterAddress = addressRepository
                .findById(rentalDTO.renterAddressId())
                .orElseThrow(() -> new NotFoundException("address", rentalDTO.renterAddressId()));

        Address renteeAddress = addressRepository.
                findById(rentalDTO.renteeAddressId())
                .orElseThrow(() -> new NotFoundException("address", rentalDTO.renteeId()));

        Item item = itemRepository
                .findById(rentalDTO.itemId())
                .orElseThrow(() -> new NotFoundException("item", rentalDTO.itemId()));

        Rental rental = new Rental(
                renter,
                rentee,
                item,
                rentalDTO.itemPriceId(),
                renterAddress,
                renteeAddress,
                rentalDTO.status(),
                rentalDTO.declaredEndDate()
        );
        return rentalRepository.save(rental);
    }

    @Transactional
    public Rental updateRental(Long id, Rental updatedRental) {
        Rental currentRental = rentalRepository.findById(id).orElseThrow(() -> new NotFoundException("rental", id));
        updatedRental.setId(id);
        updatedRental.setRentee(currentRental.getRentee());
        updatedRental.setRenter(currentRental.getRenter());
        return rentalRepository.save(updatedRental);
    }

    public void deleteRental(Long id) {
        if (!rentalRepository.existsById(id)) {
            throw new NotFoundException("rental", id);
        }
        rentalRepository.deleteById(id);
    }
}
