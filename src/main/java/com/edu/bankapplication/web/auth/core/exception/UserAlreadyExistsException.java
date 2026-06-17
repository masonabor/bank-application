package com.edu.bankapplication.web.auth.core.exception;

import lombok.experimental.StandardException;

@StandardException
public class UserAlreadyExistsException extends RuntimeException {
    private static final String USER_ALREADY_EXISTS_MESSAGE = "User with email %s already exists.";

    public UserAlreadyExistsException(String email) {
        super(USER_ALREADY_EXISTS_MESSAGE.formatted(email));
    }
}
