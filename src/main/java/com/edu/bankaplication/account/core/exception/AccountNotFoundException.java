package com.edu.bankaplication.account.core.exception;

import lombok.experimental.StandardException;

@StandardException
public class AccountNotFoundException extends RuntimeException {
    private static final String ACCOUNT_NOT_FOUND_WITH_ID = "Account not found with id: ";
    private static final String ACCOUNT_NOT_FOUND_WITH_CARD_NUMBER = "Account not found with card number: ";
    private static final String ACCOUNT_NOT_FOUND = "Account not found";

    public AccountNotFoundException(String cardNumber) {
        super(ACCOUNT_NOT_FOUND_WITH_CARD_NUMBER + cardNumber);
    }

    public AccountNotFoundException(Long id) {
        super(ACCOUNT_NOT_FOUND_WITH_ID + id);
    }
    public AccountNotFoundException() {
        super(ACCOUNT_NOT_FOUND);
    }
}
