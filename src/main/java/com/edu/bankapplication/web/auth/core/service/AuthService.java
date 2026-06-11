package com.edu.bankapplication.web.auth.core.service;

import com.edu.bankapplication.user.api.dto.CustomerResponse;
import com.edu.bankapplication.user.api.dto.EmployeeResponse;
import com.edu.bankapplication.web.auth.api.dto.*;

public interface AuthService {
    CustomerResponse registerCustomer(RegisterCustomerRequest request);
    EmployeeResponse registerEmployee(RegisterEmployeeRequest request);
    LoginResponse login(LoginRequest request);
    void activate(String activationToken);
    AccessTokenResponse refreshAccessToken(String refreshToken);
}
