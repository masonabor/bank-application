package com.edu.bankaplication.transaction.core.service.impl;

import com.edu.bankaplication.account.api.dto.PostingEntry;
import com.edu.bankaplication.account.core.exception.*;
import com.edu.bankaplication.account.core.service.PostingService;
import com.edu.bankaplication.account.persistence.AccountRepository;
import com.edu.bankaplication.account.persistence.entity.Account;
import com.edu.bankaplication.account.shared.enums.PostingType;
import com.edu.bankaplication.transaction.api.dto.TransferEntry;
import com.edu.bankaplication.transaction.api.dto.TransferResponse;
import com.edu.bankaplication.transaction.core.exception.*;
import com.edu.bankaplication.transaction.core.service.TransferService;
import com.edu.bankaplication.transaction.persistence.TransferRepository;
import com.edu.bankaplication.transaction.persistence.entity.Transfer;
import com.edu.bankaplication.transaction.shared.enums.TransactionStatus;
import com.edu.bankaplication.transaction.shared.mapper.TransferDtoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransferServiceImpl implements TransferService {
    private static final String DEBIT_DESCRIPTION = "Debit operation";
    private static final String CREDIT_DESCRIPTION = "Credit operation";

    private final TransferRepository transferRepository;
    private final TransferDtoMapper transferDtoMapper;
    private final AccountRepository accountRepository;
    private final PostingService postingService;

    // TODO implement idempotency key and checking the right of access
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public TransferResponse transfer(TransferEntry entry) {
        if (entry == null) 
            throw new EmptyTransferEntryException();

        BigDecimal amount = entry.amount();
        validateAmount(amount);

        List<Account> accounts = accountRepository.findAllByCardNumbers(Set.of(entry.fromNumber(), entry.toNumber()));

        if (accounts.isEmpty())
            throw new AccountNotFoundException();

        if (accounts.size() != 2)
            throw new DuplicatedCardNumberException();

        Account fromAccount = accounts.stream()
                .filter(account -> account.getCardNumber().equals(entry.fromNumber()))
                .findFirst()
                .orElseThrow(AccountNotFoundException::new);

        Account toAccount = accounts.stream()
                .filter(account -> account.getCardNumber().equals(entry.toNumber()))
                .findFirst()
                .orElseThrow(AccountNotFoundException::new);

        if (fromAccount.getId().equals(toAccount.getId()))
            throw new SameAccountException(fromAccount.getId());

        if (!fromAccount.getCurrency().equals(toAccount.getCurrency()))
            throw new CurrencyMismatchException();

        Account firstAccount = fromAccount.getId() < toAccount.getId() ? fromAccount : toAccount;
        Account secondAccount = fromAccount.getId() < toAccount.getId() ? toAccount : fromAccount;

        Transfer transfer = transferDtoMapper.toTransfer(entry);
        transfer.setStatus(TransactionStatus.PENDING);

        Set<PostingEntry> transferPostings = createTransferPostings(
                fromAccount,
                toAccount,
                transfer,
                entry.amount()
        );


        fromAccount.withdraw(amount);
        toAccount.deposit(amount);


        transfer.setStatus(TransactionStatus.SUCCESS);
        transferRepository.save(transfer);

        postingService.createPostings(transferPostings, transfer);

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

    private void validateAmount(BigDecimal amount) {
        if (amount == null) {
            throw new EmptyAmountException();
        }

        if (amount.signum() <= 0) {
            throw new InvalidAmountException(amount);
        }

        if (amount.scale() > 2) {
            throw new InvalidAmountScaleException(amount);
        }
    }
}
