package com.spring.rental.itemprice.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ItemPriceBaseDTO {
    private final Integer month;
    private final BigDecimal price;

    public ItemPriceBaseDTO(Integer month, BigDecimal price) {
        this.month = month;
        this.price = price;
    }
}
