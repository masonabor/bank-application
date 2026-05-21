package com.edu.bankaplication.internalService.notification.exception;

import lombok.experimental.StandardException;

@StandardException
public class EmptyNotificationMessageException extends RuntimeException {
    private static final String EMPTY_MESSAGE = "Empty notification message";
    public EmptyNotificationMessageException() {
        super(EMPTY_MESSAGE);
    }
}
