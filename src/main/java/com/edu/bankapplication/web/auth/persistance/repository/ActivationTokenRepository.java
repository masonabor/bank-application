package com.edu.bankapplication.web.auth.persistance.repository;

import com.edu.bankapplication.web.auth.persistance.entity.ActivationToken;
import jakarta.persistence.LockModeType;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ActivationTokenRepository extends JpaRepository<@NonNull ActivationToken, @NonNull Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("""
        select at\s
        from ActivationToken at\s
        where at.activationTokenHash = :tokenHash
   \s""")
    Optional<ActivationToken> findByTokenHash(@Param("tokenHash") String tokenHash);

    @Modifying
    @Query("""
        delete\s
        from ActivationToken at\s
        where at.activationTokenHash = :tokenHash
    """)
    void deleteByTokenHash(@Param("tokenHash") String tokenHash);
}
