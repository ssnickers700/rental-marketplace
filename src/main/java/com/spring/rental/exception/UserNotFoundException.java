package com.spring.rental.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String id) {
        super("Client with id " + id + " not found.");

    }
}
