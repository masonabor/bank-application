package com.edu.bankapplication.web.auth.core.exception;

import lombok.experimental.StandardException;

@StandardException
public class ActivationTokenNotFoundException extends RuntimeException {
    private static final String ACTIVATION_TOKEN_NOT_FOUND_EXCEPTION_MESSAGE = "Activation token not found by token: ";

    public ActivationTokenNotFoundException(String activationToken) {
        super(ACTIVATION_TOKEN_NOT_FOUND_EXCEPTION_MESSAGE +  activationToken);
    }
}
