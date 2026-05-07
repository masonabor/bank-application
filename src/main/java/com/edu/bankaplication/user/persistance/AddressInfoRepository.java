package com.edu.bankaplication.user.persistance;

import com.edu.bankaplication.user.persistance.entity.AddressInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressInfoRepository extends JpaRepository<AddressInfo, Long> {
}
