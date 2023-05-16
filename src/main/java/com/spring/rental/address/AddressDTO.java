package com.spring.rental.address;

public record AddressDTO(
        Long clientId,
        String street,
        String city,
        String postalCode,
        String country,
        String note
) { }
