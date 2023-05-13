package com.spring.rental.client;

import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public enum Role {
    REGULAR("regular"),
    MODERATOR("moderator"),
    ADMIN("admin");

    private final String role;
}
