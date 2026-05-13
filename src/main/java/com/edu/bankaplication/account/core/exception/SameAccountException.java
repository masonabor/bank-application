package com.edu.bankaplication.account.core.exception;

import lombok.experimental.StandardException;

@StandardException
public class SameAccountException extends RuntimeException {
    private static final String SAME_ACCOUNT_EXCEPTION_MESSAGE = "Accounts share the same identifier: ";
    public SameAccountException(Long id) {
        super(SAME_ACCOUNT_EXCEPTION_MESSAGE + id);
    }
}
