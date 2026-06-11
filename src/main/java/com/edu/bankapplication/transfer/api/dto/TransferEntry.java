package com.edu.bankapplication.transfer.api.dto;

import java.math.BigDecimal;

public record TransferEntry(
        String fromNumber,
        String toNumber,
        BigDecimal amount,
        String transferComment
) {}
