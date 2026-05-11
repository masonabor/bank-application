package com.edu.bankaplication.account.core.exception;

import lombok.experimental.StandardException;

@StandardException
public class InvalidMoneyAmountException extends RuntimeException {
    public InvalidMoneyAmountException(String amountMustBePositive) {
    }
}
