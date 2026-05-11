package com.edu.bankaplication.account.core.exception;

import lombok.experimental.StandardException;

@StandardException
public class BadAccountRequestException extends RuntimeException {
    public BadAccountRequestException(String message) {
        super(message);
    }
}
