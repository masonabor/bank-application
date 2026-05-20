package com.edu.bankaplication.web.auth.persistance.repository;

import com.edu.bankaplication.web.auth.persistance.entity.VerificationToken;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationTokenRepository extends JpaRepository<@NonNull VerificationToken, @NonNull Long> {
}
