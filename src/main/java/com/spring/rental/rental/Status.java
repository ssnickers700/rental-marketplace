package com.spring.rental.rental;

import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public enum Status {
    IN_PREPARATION("in_preparation"),
    DELIVERY_ORDERED("delivery_ordered"),
    IN_DELIVERY("in_delivery"),
    DELIVERED("delivered"),
    RETURN_ORDERED("return_ordered"),
    IN_RETURN("in_return"),
    RETURNED("returned"),
    ENDED("ended"),
    CANCELLED("cancelled"),
    BOUGHT_OUT("bought_out");

    private final String role;
}
