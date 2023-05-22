package com.spring.rental.item.dto;

import com.spring.rental.item.State;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;


@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ItemPayloadDTO extends ItemBaseDTO {
    private final Long clientId;
    private final Long categoryId;

    public ItemPayloadDTO(
            String title,
            String description,
            BigDecimal basePrice,
            LocalDate availabilityDeadline,
            State state,
            Long clientId,
            Long categoryId
    ) {
        super(title, description, basePrice, availabilityDeadline, state);
        this.clientId = clientId;
        this.categoryId = categoryId;
    }
}
