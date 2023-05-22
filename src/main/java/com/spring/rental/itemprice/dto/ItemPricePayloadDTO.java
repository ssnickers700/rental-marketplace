package com.spring.rental.itemprice.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public final class ItemPricePayloadDTO extends ItemPriceBaseDTO {
    private final Long itemId;

    public ItemPricePayloadDTO(Integer month, BigDecimal price, Long itemId) {
        super(month, price);
        this.itemId = itemId;
    }
}
