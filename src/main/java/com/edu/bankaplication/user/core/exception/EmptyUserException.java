package com.edu.bankaplication.user.core.exception;

import lombok.experimental.StandardException;

@StandardException
public class EmptyUserException extends RuntimeException {
    private static final String EMPTY_USER_EXCEPTION_MESSAGE = "User is null or empty";

    public EmptyUserException() {
        super(EMPTY_USER_EXCEPTION_MESSAGE);
    }
}
