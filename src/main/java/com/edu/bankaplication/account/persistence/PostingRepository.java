package com.edu.bankaplication.account.persistence;

import com.edu.bankaplication.account.persistence.entity.Posting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostingRepository extends JpaRepository<Posting, Long> {
    Optional<List<Posting>> findAllByAccountId(Long accountId);
}
