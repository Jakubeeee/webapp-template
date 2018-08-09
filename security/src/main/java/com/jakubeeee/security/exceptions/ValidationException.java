package com.jakubeeee.security.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "Provided data is invalid")
public class ValidationException extends Exception {

    public ValidationException(String message) {
        super(message);
    }
}
