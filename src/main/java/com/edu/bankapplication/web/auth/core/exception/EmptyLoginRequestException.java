package com.edu.bankapplication.web.auth.core.exception;

import lombok.experimental.StandardException;

@StandardException
public class EmptyLoginRequestException extends RuntimeException {
    private static final String EXCEPTION_MESSAGE = "Empty Login Request";

    public EmptyLoginRequestException() {
        super(EXCEPTION_MESSAGE);
    }
}
