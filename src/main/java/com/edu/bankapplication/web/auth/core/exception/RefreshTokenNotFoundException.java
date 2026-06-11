package com.edu.bankapplication.web.auth.core.exception;

import lombok.experimental.StandardException;

@StandardException
public class RefreshTokenNotFoundException extends RuntimeException {
    private static final String REFRESH_TOKEN_EXCEPTION_MESSAGE = "Refresh token not found: ";

    public RefreshTokenNotFoundException(String token) {
        super(REFRESH_TOKEN_EXCEPTION_MESSAGE + token);
    }
}
