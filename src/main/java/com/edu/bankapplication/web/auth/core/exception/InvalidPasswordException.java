package com.edu.bankapplication.web.auth.core.exception;

import lombok.experimental.StandardException;

@StandardException
public class InvalidPasswordException extends RuntimeException {
    private static final String INVALID_PASSWORD_EXCEPTION_MESSAGE = "Invalid Password for user: ";

    public InvalidPasswordException(Long id) {
        super(INVALID_PASSWORD_EXCEPTION_MESSAGE + id);
    }
}
