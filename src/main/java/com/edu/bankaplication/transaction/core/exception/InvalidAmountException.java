package com.edu.bankaplication.transaction.core.exception;

import lombok.experimental.StandardException;

import java.math.BigDecimal;

@StandardException
public class InvalidAmountException extends RuntimeException {
    private static final String INVALID_AMOUNT_EXCEPTION = "Amount must be positive: ";

    public InvalidAmountException(BigDecimal amount) {
        super(INVALID_AMOUNT_EXCEPTION + amount);
    }
}
