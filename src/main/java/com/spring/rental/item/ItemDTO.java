package com.spring.rental.item;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ItemDTO(
        Long clientId,
        String title,
        String description,
        BigDecimal basePrice,
        LocalDateTime availabilityDeadline,
        State state
) { }
