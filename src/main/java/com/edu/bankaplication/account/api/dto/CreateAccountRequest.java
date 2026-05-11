package com.edu.bankaplication.account.api.dto;

public record CreateAccountRequest(
        String cardNumber,
        Long userId
) {}
