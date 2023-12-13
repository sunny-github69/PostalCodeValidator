package com.example.PostalCodeValidator.Exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class PostalCodeValidationException extends RuntimeException {
    private final HttpStatus httpStatus;

    public PostalCodeValidationException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

}
