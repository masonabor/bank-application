package com.edu.bankaplication.user.core.exception;

import lombok.experimental.StandardException;

@StandardException
public class EmptyCreateUserRequestException extends RuntimeException {
    private static final String EMPTY_CREATE_USER_REQUEST_EXCEPTION = "Empty createUserRequest";

    public EmptyCreateUserRequestException() {
        super(EMPTY_CREATE_USER_REQUEST_EXCEPTION);
    }
}
