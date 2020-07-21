package com.ovi.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserEmailException extends Exception {

    public UserEmailException(String message) {
        super(message);
    }
}
