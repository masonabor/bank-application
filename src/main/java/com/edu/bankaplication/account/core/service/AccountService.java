package com.edu.bankaplication.account.core.service;

import com.edu.bankaplication.account.api.dto.AccountResponse;
import com.edu.bankaplication.account.api.dto.CreateAccountRequest;

public interface AccountService {
    AccountResponse createAccount(CreateAccountRequest request);
    void deleteAccount(Long id);
}
