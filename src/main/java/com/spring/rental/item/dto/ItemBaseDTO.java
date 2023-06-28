package com.spring.rental.item.dto;

import com.spring.rental.item.State;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ItemBaseDTO {
    private final String title;
    private final String description;
    private final BigDecimal basePrice;
    private final LocalDate availabilityDeadline;
    private final State state;
}
