package com.edu.bankaplication.user.persistance;

import com.edu.bankaplication.user.persistance.entity.customer.AddressInfo;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressInfoRepository extends JpaRepository<@NonNull AddressInfo, @NonNull Long> {
}
