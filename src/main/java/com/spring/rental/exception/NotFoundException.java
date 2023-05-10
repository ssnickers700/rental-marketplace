package com.spring.rental.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String entityName, Long id) {
        super(entityName + " with id " + id + " not found");
    }
}
