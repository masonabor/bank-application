package com.edu.bankapplication.user.core.exception;

public class UserStatusException extends RuntimeException {
    private static final String USER_STATUS_EXCEPTION_MESSAGE = "User status is not ACTIVATED: ";

    public UserStatusException(Long id) {
        super(USER_STATUS_EXCEPTION_MESSAGE + id);
    }
}
