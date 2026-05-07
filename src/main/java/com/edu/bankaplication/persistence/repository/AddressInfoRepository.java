package com.edu.bankaplication.persistence.repository;

import com.edu.bankaplication.domain.user.AddressInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressInfoRepository extends JpaRepository<AddressInfo, Long> {
}
