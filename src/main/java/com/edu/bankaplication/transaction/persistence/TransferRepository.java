package com.edu.bankaplication.transaction.persistence;

import com.edu.bankaplication.transaction.persistence.entity.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, Long> {

}
