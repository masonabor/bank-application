package com.edu.bankaplication.transaction.api.dto;

import com.edu.bankaplication.account.api.dto.PostingResponse;
import com.edu.bankaplication.account.shared.enums.Currency;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;

public record TransferResponse(
        String fromNumber,
        Currency currency,
        BigDecimal amount,
        String transferComment,
        Instant createdAt,
        Set<PostingResponse> transferPostings
) {}
