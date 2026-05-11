package com.edu.bankaplication.user.core.exception;

import lombok.experimental.StandardException;

@StandardException
public class UserNotExistsException extends RuntimeException {
    public UserNotExistsException(Long id) {
        super("User with id " + id + " does not exist");
    }
}
