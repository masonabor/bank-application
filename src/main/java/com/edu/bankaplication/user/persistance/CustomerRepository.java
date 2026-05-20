package com.edu.bankaplication.user.persistance;

import com.edu.bankaplication.user.persistance.entity.customer.Customer;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<@NonNull Customer, @NonNull Long> {
    boolean existsById(Long id);
}
