package com.dev.biostoreapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Product was not found.")
public class ProductNotFoundException extends RuntimeException{
    private Long id;

    public ProductNotFoundException(Long id) {
        super("Product with ID " + id + " not found!");
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
