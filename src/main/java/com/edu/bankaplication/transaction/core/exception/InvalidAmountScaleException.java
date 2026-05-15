package com.edu.bankaplication.transaction.core.exception;

import lombok.experimental.StandardException;

import java.math.BigDecimal;

@StandardException
public class InvalidAmountScaleException extends RuntimeException {
    private static final String INVALID_AMOUNT_SCALE_EXCEPTION = "Invalid scale for: ";

    public InvalidAmountScaleException(BigDecimal amount) {
        super(INVALID_AMOUNT_SCALE_EXCEPTION + amount);
    }
}
