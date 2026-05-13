package com.edu.bankaplication.account.core.service.impl;

import com.edu.bankaplication.account.api.dto.AccountResponse;
import com.edu.bankaplication.account.api.dto.CreateAccountRequest;
import com.edu.bankaplication.account.core.exception.AccountNotFoundException;
import com.edu.bankaplication.account.core.exception.BadAccountRequestException;
import com.edu.bankaplication.account.core.service.AccountService;
import com.edu.bankaplication.account.persistence.AccountRepository;
import com.edu.bankaplication.account.persistence.entity.Account;
import com.edu.bankaplication.account.shared.mapper.AccountDtoMapper;
import com.edu.bankaplication.user.core.exception.UserNotExistsException;
import com.edu.bankaplication.user.core.service.UserService;
import com.edu.bankaplication.user.persistance.UserRepository;
import com.edu.bankaplication.user.persistance.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountDtoMapper accountDtoMapper;
    private final UserService userService;
    private final UserRepository userRepository;

    //TODO validation of user, which will own that account or that administrator creates the account for user
    @Override
    @Transactional
    public AccountResponse createAccount(CreateAccountRequest request) {
        if (request == null) {
            log.error("request is null");
            throw new BadAccountRequestException("Create account request is null");
        }

        User user = userRepository.findById(request.userId())
                .orElseThrow(
                        () -> new UserNotExistsException(request.userId())
                );
        Account savedAccount = accountRepository.save(accountDtoMapper.toAccount(request, user));
        return accountDtoMapper.toAccountResponse(savedAccount);
    }

    @Override
    public void deleteAccount(Long id) {

    }

    @Override
    @Transactional(readOnly = true)
    public boolean checkBalance(Long fromId, BigDecimal amountToWithdraw) {
        Account fromAccount = accountRepository.findById(fromId)
                .orElseThrow(() -> new AccountNotFoundException(fromId));

        return fromAccount.getBalance().compareTo(amountToWithdraw) > 0;
    }


}
