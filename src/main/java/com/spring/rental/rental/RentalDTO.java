package com.spring.rental.rental;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class RentalDTO {
    private Long renterId;
    private Long renteeId;
    private Long itemId;
    private Long itemPriceId;
    private Long renterAddressId;
    private Long renteeAddressId;
    private Status status;
    private LocalDateTime declaredEndDate;
}
