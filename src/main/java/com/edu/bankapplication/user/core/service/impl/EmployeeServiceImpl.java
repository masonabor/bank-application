package com.edu.bankapplication.user.core.service.impl;

import com.edu.bankapplication.user.api.dto.EmployeeResponse;
import com.edu.bankapplication.user.core.service.EmployeeService;
import com.edu.bankapplication.web.auth.api.dto.RegisterEmployeeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    @Override
    public EmployeeResponse createEmployee(RegisterEmployeeRequest request) {
        return null;
    }
}
