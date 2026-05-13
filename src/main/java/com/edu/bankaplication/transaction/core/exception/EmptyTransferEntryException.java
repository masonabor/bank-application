package com.edu.bankaplication.transaction.core.exception;

import lombok.experimental.StandardException;

@StandardException
public class EmptyTransferEntryException extends RuntimeException {
    private static final String EMPTY_TRANSFER_ENTRY_EXCEPTION_MESSAGE = "Empty Transfer Entry";

    public EmptyTransferEntryException() {
        super(EMPTY_TRANSFER_ENTRY_EXCEPTION_MESSAGE);
    }
}
