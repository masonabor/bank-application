package com.edu.bankaplication.account.core.exception;

import lombok.experimental.StandardException;

@StandardException
public class EmptyCardNumberException extends RuntimeException {
    private static final String EMPTY_CARD_NUMBER_EXCEPTION_MESSAGE = "Empty Card Number";
    public EmptyCardNumberException() {
        super(EMPTY_CARD_NUMBER_EXCEPTION_MESSAGE);
    }
}
