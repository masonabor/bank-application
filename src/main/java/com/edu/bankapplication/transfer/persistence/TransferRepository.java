package com.edu.bankapplication.transfer.persistence;

import com.edu.bankapplication.transfer.persistence.entity.Transfer;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferRepository extends JpaRepository<@NonNull Transfer, @NonNull Long> {

}
