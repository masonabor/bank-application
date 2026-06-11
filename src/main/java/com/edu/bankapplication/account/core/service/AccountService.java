package com.edu.bankapplication.account.core.service;

import com.edu.bankapplication.account.api.dto.AccountResponse;
import com.edu.bankapplication.account.api.dto.CreateAccountRequest;

import java.math.BigDecimal;

public interface AccountService {
    AccountResponse createAccount(CreateAccountRequest request);
    void deleteAccount(Long id);
    boolean checkBalance(Long id, BigDecimal amount);
}
