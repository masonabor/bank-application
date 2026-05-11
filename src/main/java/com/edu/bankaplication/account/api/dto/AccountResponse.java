package com.edu.bankaplication.account.api.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record AccountResponse(
        String cardNumber,
        BigDecimal balance,
        Long userId,
        LocalDateTime dueTo
) {}
