package com.edu.bankapplication.account.core.exception;

import lombok.experimental.StandardException;

@StandardException
public class InvalidBalanceAmountException extends RuntimeException {
    private static final String INVALID_BALANCE_AMOUNT_MESSAGE = "Invalid balance amount for account with id: ";
    public InvalidBalanceAmountException(Long id, Number balance) {
        super(INVALID_BALANCE_AMOUNT_MESSAGE + id + ". Balance: " + balance);
    }
}
