package com.edu.bankapplication.user.core.service;

import com.edu.bankapplication.user.api.dto.CustomerResponse;
import com.edu.bankapplication.web.auth.api.dto.RegisterCustomerRequest;

public interface CustomerService {
    CustomerResponse createCustomer(RegisterCustomerRequest request);
    void deleteCustomer(Long id);
    boolean existsById(Long id);
    CustomerResponse findById(Long id);
}
