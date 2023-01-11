package com.crud.demo.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class HttpStatusException extends RuntimeException {

    @Getter
    private final HttpStatus status;

    public HttpStatusException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
