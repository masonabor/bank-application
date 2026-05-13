package com.edu.bankaplication.account.api.dto;

import com.edu.bankaplication.account.shared.enums.PostingType;

import java.math.BigDecimal;

public record PostingResponse(
        Long accountId,
        PostingType type,
        BigDecimal amount,
        String description
) {}
