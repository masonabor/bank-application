package com.edu.bankaplication.account.core.exception;

import lombok.experimental.StandardException;

@StandardException
public class IllegalAmountException extends RuntimeException {

    public IllegalAmountException(String message) {
        super(message);
    }
}
