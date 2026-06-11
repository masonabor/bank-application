package com.edu.bankapplication.user.persistance;

import com.edu.bankapplication.user.persistance.entity.customer.Customer;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<@NonNull Customer, @NonNull Long> {
    boolean existsById(Long id);
}
