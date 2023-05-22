package com.spring.rental.item.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.spring.rental.item.State;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@JsonPropertyOrder({"id"})
public class ItemResponseDTO extends ItemBaseDTO {
    private Long id;

    public ItemResponseDTO(
            String title,
            String description,
            BigDecimal basePrice,
            LocalDate availabilityDeadline,
            State state,
            Long id
    ) {
        super(title, description, basePrice, availabilityDeadline, state);
        this.id = id;
    }
}
