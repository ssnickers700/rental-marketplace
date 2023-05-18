package com.spring.rental.item;

import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public enum State {
    ACTIVE("active"),
    INACTIVE("inactive"),
    RENTED("rented"),
    BOUGHT_OUT("bought_out");

    private final String state;
}
