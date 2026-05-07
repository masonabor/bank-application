package com.edu.bankaplication.persistence.repository;

import com.edu.bankaplication.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
