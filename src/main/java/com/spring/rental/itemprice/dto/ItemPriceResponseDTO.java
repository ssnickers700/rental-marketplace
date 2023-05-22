package com.spring.rental.itemprice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.spring.rental.item.dto.ItemNestedResponseDTO;
import com.spring.rental.item.dto.ItemPayloadDTO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@JsonPropertyOrder({"id"})
public final class ItemPriceResponseDTO extends ItemPriceBaseDTO {
    @JsonProperty("item")
    private final ItemNestedResponseDTO itemPayloadDTO;
    private final Long id;


    public ItemPriceResponseDTO(Integer month, BigDecimal price, Long id, ItemNestedResponseDTO itemPayloadDTO) {
        super(month, price);
        this.id = id;
        this.itemPayloadDTO = itemPayloadDTO;
    }
}
