package com.edu.bankapplication.account.api.dto;

import com.edu.bankapplication.account.shared.enums.Currency;

public record CreateAccountRequest(
        Long userId,
        String cardNumber,
        Currency currency
) {}
