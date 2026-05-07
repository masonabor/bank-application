package com.edu.bankaplication.persistence.repository;

import com.edu.bankaplication.domain.account.Posting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostingRepository extends JpaRepository<Posting, Long> {
}
