package com.edu.bankapplication.account.api.dto;

import com.edu.bankapplication.account.persistence.entity.Account;
import com.edu.bankapplication.account.shared.enums.PostingType;
import com.edu.bankapplication.transfer.persistence.entity.Transfer;

import java.math.BigDecimal;

public record PostingEntry(
        Account account,
        PostingType type,
        BigDecimal amount,
        String description,
        Transfer transfer
) {
    public static PostingEntry of(
            Account account,
            PostingType type,
            BigDecimal amount,
            String description,
            Transfer transfer
    ) {
        return new PostingEntry(
                account,
                type,
                amount,
                description,
                transfer
        );
    }
}
