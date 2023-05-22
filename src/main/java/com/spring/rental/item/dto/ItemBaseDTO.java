package com.spring.rental.item.dto;

import com.spring.rental.item.State;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ItemBaseDTO {
    private final String title;
    private final String description;
    private final BigDecimal basePrice;
    private final LocalDate availabilityDeadline;
    private final State state;

    public ItemBaseDTO(
            String title,
            String description,
            BigDecimal basePrice,
            LocalDate availabilityDeadline,
            State state
    ) {
        this.title = title;
        this.description = description;
        this.basePrice = basePrice;
        this.availabilityDeadline = availabilityDeadline;
        this.state = state;
    }
}
