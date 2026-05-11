package com.edu.bankaplication.transaction.core.service;

import com.edu.bankaplication.transaction.api.dto.TransferEntry;
import com.edu.bankaplication.transaction.api.dto.TransferResponse;

public interface TransferService {
    TransferResponse transfer(TransferEntry entry);
}
