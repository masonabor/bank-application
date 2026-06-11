package com.edu.bankapplication.account.api.dto;

import java.math.BigDecimal;
import java.time.Instant;

public record AccountResponse(
        String cardNumber,
        BigDecimal balance,
        Long userId,
        Instant dueTo
) {}
