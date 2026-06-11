package com.edu.bankapplication.transfer.core.service;

import com.edu.bankapplication.transfer.api.dto.TransferEntry;
import com.edu.bankapplication.transfer.api.dto.TransferResponse;

public interface TransferService {
    TransferResponse transfer(TransferEntry entry);
}
