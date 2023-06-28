package com.spring.rental.itemprice.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
public class ItemPriceBaseDTO {
    private final Integer month;
    private final BigDecimal price;
}
