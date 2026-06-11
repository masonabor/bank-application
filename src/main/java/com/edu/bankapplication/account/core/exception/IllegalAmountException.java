package com.edu.bankapplication.account.core.exception;

import lombok.experimental.StandardException;

@StandardException
public class IllegalAmountException extends RuntimeException {

    public IllegalAmountException(String message) {
        super(message);
    }
}
