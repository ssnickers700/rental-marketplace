package com.spring.rental.rental;

import java.time.LocalDateTime;

public record RentalDTO(
        Long renterId,
        Long renteeId,
        Long itemId,
        Long itemPriceId,
        Long renterAddressId,
        Long renteeAddressId,
        Status status,
        LocalDateTime declaredEndDate
) { }
