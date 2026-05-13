package com.edu.bankaplication.account.core.exception;

import lombok.experimental.StandardException;

@StandardException
public class EmptyPostingEntryException extends RuntimeException {
    private static final String EMPTY_POSTING_ENTRY_EXCEPTION_MESSAGE = "Empty posting entry";
    public EmptyPostingEntryException() {
        super(EMPTY_POSTING_ENTRY_EXCEPTION_MESSAGE);
    }
}
