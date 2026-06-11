package com.edu.bankapplication.user.core.service;

import com.edu.bankapplication.user.api.dto.EmployeeResponse;
import com.edu.bankapplication.web.auth.api.dto.RegisterEmployeeRequest;

public interface EmployeeService {
    EmployeeResponse createEmployee(RegisterEmployeeRequest request);
}
