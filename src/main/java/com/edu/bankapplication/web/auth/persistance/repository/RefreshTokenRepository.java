package com.edu.bankapplication.web.auth.persistance.repository;

import com.edu.bankapplication.user.persistance.entity.IdentityUser;
import com.edu.bankapplication.web.auth.persistance.entity.RefreshToken;
import jakarta.persistence.LockModeType;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<@NonNull RefreshToken, @NonNull Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("""
        select rt\s
        from RefreshToken rt\s
        where rt.refreshTokenHash = :tokenHash
    """)
    Optional<RefreshToken> findByTokenHash(@Param("tokenHash") String tokenHash);

    @Modifying
    @Query("""
        delete
        from RefreshToken rt
        where rt.refreshTokenHash = :tokenHash
    """)
    void deleteByTokenHash(@Param("tokenHash") String tokenHash);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    void deleteByUser(IdentityUser user);

    boolean existsByUser(IdentityUser user);
}
