package com.edu.bankaplication.account.core.exception;

import lombok.experimental.StandardException;

@StandardException
public class DuplicatedCardNumberException extends RuntimeException{
    private static final String DUPLICATED_CARD_NUMBER_EXCEPTION_MESSAGE = "Duplicated Card Number: ";

    public DuplicatedCardNumberException(String cardNumber) {
        super(DUPLICATED_CARD_NUMBER_EXCEPTION_MESSAGE + cardNumber);
    }
}
