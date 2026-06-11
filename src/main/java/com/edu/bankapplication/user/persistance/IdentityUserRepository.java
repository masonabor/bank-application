package com.edu.bankapplication.user.persistance;

import com.edu.bankapplication.user.persistance.entity.IdentityUser;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IdentityUserRepository extends JpaRepository<@NonNull IdentityUser, @NonNull Long> {
    Optional<IdentityUser> findByEmail(String email);
}
