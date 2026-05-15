package com.edu.bankaplication.account.core.exception;

import lombok.experimental.StandardException;

@StandardException
public class AccountLockedException extends RuntimeException {
    private static final String ACCOUNT_LOCKED_EXCEPTION_MESSAGE = "Account is locked with id: ";

    public AccountLockedException(Long id) {
        super(ACCOUNT_LOCKED_EXCEPTION_MESSAGE + id);
    }
}
