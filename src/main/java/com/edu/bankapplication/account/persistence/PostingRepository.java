package com.edu.bankapplication.account.persistence;

import com.edu.bankapplication.account.persistence.entity.Posting;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostingRepository extends JpaRepository<@NonNull Posting, @NonNull Long> {
    Optional<List<Posting>> findAllByAccountId(Long accountId);
}
