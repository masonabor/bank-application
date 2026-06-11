package com.edu.bankapplication.account.api.dto;

public record CreateAccountRequest(
        String cardNumber,
        Long userId
) {}
