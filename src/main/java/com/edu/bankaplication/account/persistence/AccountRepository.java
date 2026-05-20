package com.edu.bankaplication.account.persistence;

import com.edu.bankaplication.account.persistence.entity.Account;
import jakarta.persistence.LockModeType;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface AccountRepository extends JpaRepository<@NonNull Account, @NonNull Long> {

    /*
        it is necessary because you need to lock accounts
        in the same order ever if it will be another transfer
        with these accounts, but in other order, so you need to
        guarantee the same order for locking in the same order
        to prevent deadlock
    */
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("""
        select a\s
        from Account a\s
        where a.cardNumber in :cardNumbers\s
        order by a.id
    """)
    List<Account> findAllByCardNumbers(Set<String> cardNumbers);
}
