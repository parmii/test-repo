package com.solactive.ticksconsumerservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TickNotFoundException extends RuntimeException {
    public TickNotFoundException(String message) {
        super(message);
    }
}
