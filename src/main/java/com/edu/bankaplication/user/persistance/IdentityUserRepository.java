package com.edu.bankaplication.user.persistance;

import com.edu.bankaplication.user.persistance.entity.IdentityUser;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdentityUserRepository extends JpaRepository<@NonNull IdentityUser, @NonNull Long> {
}
