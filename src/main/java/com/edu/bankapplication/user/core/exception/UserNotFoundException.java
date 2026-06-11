package com.edu.bankapplication.user.core.exception;

public class UserNotFoundException extends RuntimeException {
    private static final String USER_NOT_FOUND = "User not found with email: ";

    public UserNotFoundException(String email) {
        super(USER_NOT_FOUND + email);
    }
}
