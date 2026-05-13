package com.edu.bankaplication.transaction.core.service.impl;

import com.edu.bankaplication.account.api.dto.PostingEntry;
import com.edu.bankaplication.account.core.exception.*;
import com.edu.bankaplication.account.core.service.PostingService;
import com.edu.bankaplication.account.persistence.AccountRepository;
import com.edu.bankaplication.account.persistence.entity.Account;
import com.edu.bankaplication.account.shared.enums.PostingType;
import com.edu.bankaplication.account.shared.enums.Status;
import com.edu.bankaplication.transaction.api.dto.TransferEntry;
import com.edu.bankaplication.transaction.api.dto.TransferResponse;
import com.edu.bankaplication.transaction.core.exception.EmptyTransferEntryException;
import com.edu.bankaplication.transaction.core.service.TransferService;
import com.edu.bankaplication.transaction.persistence.TransferRepository;
import com.edu.bankaplication.transaction.persistence.entity.Transfer;
import com.edu.bankaplication.transaction.shared.enums.TransactionStatus;
import com.edu.bankaplication.transaction.shared.mapper.TransferDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TransferServiceImpl implements TransferService {
    private static final String DEBIT_DESCRIPTION = "Debit operation";
    private static final String CREDIT_DESCRIPTION = "Credit operation";

    private final TransferRepository transferRepository;
    private final TransferDtoMapper transferDtoMapper;
    private final AccountRepository accountRepository;
    private final PostingService postingService;

    @Override
    @Transactional
    public TransferResponse transfer(TransferEntry entry) {
        if (entry == null) 
            throw new EmptyTransferEntryException();

        Account fromAccount = checkAndGetAccountByCardNumber(entry.fromNumber());
        Account toAccount = checkAndGetAccountByCardNumber(entry.toNumber());
        BigDecimal amount = entry.amount();

        if (fromAccount.getId().equals(toAccount.getId()))
            throw new SameAccountException(fromAccount.getId());

        fromAccount.setStatus(Status.LOCKED);
        toAccount.setStatus(Status.LOCKED);
        accountRepository.saveAll(Set.of(fromAccount, toAccount));

        Transfer transfer = transferDtoMapper.toTransfer(entry);
        transfer.setStatus(TransactionStatus.PENDING);

        Set<PostingEntry> transferPostings = createTransferPostings(
                fromAccount,
                toAccount,
                transfer,
                entry.amount()
        );

        postingService.createPostings(transferPostings, transfer);

        fromAccount.withdraw(amount);
        toAccount.deposit(amount);

        transfer.setStatus(TransactionStatus.SUCCESS);
        transferRepository.save(transfer);

        fromAccount.setStatus(Status.READY);
        toAccount.setStatus(Status.READY);
        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);

        return transferDtoMapper.toTransferResponse(transfer);
    }

    private Set<PostingEntry> createTransferPostings(
            Account fromAccount,
            Account toAccount,
            Transfer transfer,
            BigDecimal amount
    ) {
        PostingEntry fromAccountPosting = PostingEntry.of(
                fromAccount,
                PostingType.DEBIT,
                amount,
                DEBIT_DESCRIPTION,
                transfer);

        PostingEntry toAccountPosting = PostingEntry.of(
                toAccount,
                PostingType.CREDIT,
                amount,
                CREDIT_DESCRIPTION,
                transfer
        );

        return Set.of(fromAccountPosting, toAccountPosting);
    }

    private Account checkAndGetAccountByCardNumber(String number) {
        if (number == null || number.isBlank())
            throw new EmptyCardNumberException();

        List<Account> accounts = accountRepository.findAllByCardNumber(number);

        if (accounts.isEmpty())
            throw new AccountNotFoundException(number);

        if (accounts.size() != 1)
            throw new DuplicatedCardNumberException(number);

        Account account = accounts.getFirst();

        if (account.getBalance().compareTo(BigDecimal.ZERO) < 0)
            throw new InvalidBalanceAmountException(account.getId(), account.getBalance());

        return account;
    }


}
