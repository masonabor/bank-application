package com.edu.bankaplication.user.core.service;

import com.edu.bankaplication.user.api.dto.CreateCustomerRequest;
import com.edu.bankaplication.user.api.dto.CustomerResponse;
import com.edu.bankaplication.user.api.dto.RegisterCustomerRequest;

public interface CustomerService {
    CustomerResponse createCustomer(RegisterCustomerRequest request);
    void deleteCustomer(Long id);
    boolean existsById(Long id);
    CustomerResponse findById(Long id);
}
