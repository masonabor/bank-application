package com.edu.bankaplication.user.core.service;

import com.edu.bankaplication.user.api.dto.CreateCustomerRequest;
import com.edu.bankaplication.user.api.dto.CustomerResponse;

public interface CustomerService {
    CustomerResponse createUser(CreateCustomerRequest request);
    void deleteUser(Long id);
    boolean existsById(Long id);
    CustomerResponse findById(Long id);
}
