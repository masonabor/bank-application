package com.edu.bankaplication.account.persistence;

import com.edu.bankaplication.account.persistence.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findAllByCardNumber(String cardNumber);
}
