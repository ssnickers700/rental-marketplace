package com.spring.rental.category.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@JsonPropertyOrder({"id"})
public final class CategoryResponseDTO extends CategoryPayloadDTO {
    private final Long id;

    public CategoryResponseDTO(String name, Long id) {
        super(name);
        this.id = id;
    }
}
