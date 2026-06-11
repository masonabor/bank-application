package com.edu.bankapplication.account.api.dto;

import com.edu.bankapplication.account.shared.enums.PostingType;

import java.math.BigDecimal;

public record PostingResponse(
        Long accountId,
        PostingType type,
        BigDecimal amount,
        String description
) {}
