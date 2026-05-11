package com.edu.bankaplication.transaction.core.service;

import com.edu.bankaplication.transaction.api.dto.TransactionEntry;
import com.edu.bankaplication.transaction.api.dto.TransactionResponse;

public interface TransactionService {
    TransactionResponse transfer(TransactionEntry entry);
}
