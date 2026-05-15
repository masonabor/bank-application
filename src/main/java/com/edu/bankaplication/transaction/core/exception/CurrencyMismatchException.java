package com.edu.bankaplication.transaction.core.exception;

import lombok.experimental.StandardException;

@StandardException
public class CurrencyMismatchException extends RuntimeException {
    private static final String CURRENCY_MISMATCH_MESSAGE = "Currency mismatch";

    public CurrencyMismatchException() {
        super(CURRENCY_MISMATCH_MESSAGE);
    }
}
