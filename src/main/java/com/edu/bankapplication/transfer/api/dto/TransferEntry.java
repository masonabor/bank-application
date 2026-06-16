package com.edu.bankapplication.transfer.api.dto;

import com.edu.bankapplication.account.shared.enums.Currency;

import java.math.BigDecimal;

public record TransferEntry(
        String fromNumber,
        String toNumber,
        Currency currency,
        BigDecimal amount,
        String transferComment
) {}
