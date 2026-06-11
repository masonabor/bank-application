package com.edu.bankapplication.web.auth.core.exception;

import lombok.experimental.StandardException;

@StandardException
public class EmptyRefreshTokenException extends RuntimeException {
    private static final String EMPTY_REFRESH_TOKEN_EXCEPTION_MESSAGE = "Empty Refresh Token Exception";

    public EmptyRefreshTokenException() {
        super(EMPTY_REFRESH_TOKEN_EXCEPTION_MESSAGE);
    }
}
