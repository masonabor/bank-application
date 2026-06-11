package com.edu.bankapplication.account.core.exception;

import lombok.experimental.StandardException;

@StandardException
public class InsufficientBalanceException extends RuntimeException {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}
