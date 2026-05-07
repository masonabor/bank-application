package com.edu.bankaplication.persistence.repository;

import com.edu.bankaplication.domain.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
