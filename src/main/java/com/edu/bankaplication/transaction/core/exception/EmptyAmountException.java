package com.edu.bankaplication.transaction.core.exception;

import lombok.experimental.StandardException;

@StandardException
public class EmptyAmountException extends RuntimeException {
    private static final String EMPTY_AMOUNT_EXCEPTION = "Empty amount exception";

    public EmptyAmountException() {
        super(EMPTY_AMOUNT_EXCEPTION);
    }
}
