package com.dev.biostoreapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicatedReviewException extends RuntimeException{

    public DuplicatedReviewException(String message) {
        super(message);
    }
}
