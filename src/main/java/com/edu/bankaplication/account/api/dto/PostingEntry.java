package com.edu.bankaplication.account.api.dto;

import com.edu.bankaplication.account.persistence.entity.Account;
import com.edu.bankaplication.account.shared.enums.PostingType;
import com.edu.bankaplication.transaction.persistence.entity.Transfer;

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
