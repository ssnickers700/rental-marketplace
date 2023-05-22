package com.spring.rental.item.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.spring.rental.item.State;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@JsonPropertyOrder({"id"})
public class ItemNestedResponseDTO extends ItemPayloadDTO {
    private final Long id;

    public ItemNestedResponseDTO(String title, String description, BigDecimal basePrice, LocalDate availabilityDeadline, State state, Long clientId, Long categoryId, Long id) {
        super(title, description, basePrice, availabilityDeadline, state, clientId, categoryId);
        this.id = id;
    }
}
