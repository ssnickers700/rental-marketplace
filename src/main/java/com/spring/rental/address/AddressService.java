package com.spring.rental.address;

import com.spring.rental.client.Client;
import com.spring.rental.client.ClientRepository;
import com.spring.rental.exception.NotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    private final AddressRepository addressRepository;
    private final ClientRepository clientRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository,
                          ClientRepository clientRepository) {
        this.addressRepository = addressRepository;
        this.clientRepository = clientRepository;
    }

    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    public Address getAddressById(Long id) {
        return addressRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("address", id));
    }

    @Transactional
    public Address createAddress(AddressDTO addressDTO) {
        Client client = clientRepository.findById(addressDTO.clientId()).orElseThrow(
                () -> new NotFoundException("client", addressDTO.clientId())
        );
        Address address = new Address(
                client,
                addressDTO.street(),
                addressDTO.city(),
                addressDTO.postalCode(),
                addressDTO.country()
        );
        address.setNote(addressDTO.note() != null ? addressDTO.note() : null);
        return addressRepository.save(address);
    }

    @Transactional
    public Address updateAddress(Long id, Address updatedAddress) {
        Address currentAddress = addressRepository.findById(id).orElseThrow(() -> new NotFoundException("address", id));
        updatedAddress.setId(id);
        updatedAddress.setClient(currentAddress.getClient());
        return addressRepository.save(updatedAddress);
    }

    public void deleteAddress(Long id) {
        if (!addressRepository.existsById(id)) {
            throw new NotFoundException("address", id);
        }
        addressRepository.deleteById(id);
    }
}

