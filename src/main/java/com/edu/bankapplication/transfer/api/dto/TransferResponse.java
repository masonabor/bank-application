package com.edu.bankapplication.transfer.api.dto;

import com.edu.bankapplication.account.api.dto.PostingResponse;
import com.edu.bankapplication.account.shared.enums.Currency;

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
