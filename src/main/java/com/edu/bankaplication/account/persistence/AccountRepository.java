package com.edu.bankaplication.account.persistence;

import com.edu.bankaplication.account.persistence.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
