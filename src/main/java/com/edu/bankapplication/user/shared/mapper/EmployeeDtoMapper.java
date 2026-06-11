package com.edu.bankapplication.user.shared.mapper;

import com.edu.bankapplication.user.api.dto.EmployeeResponse;
import com.edu.bankapplication.user.persistance.entity.employee.Employee;
import com.edu.bankapplication.web.auth.api.dto.RegisterEmployeeRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeDtoMapper {

    Employee toEmployee(RegisterEmployeeRequest request);

    EmployeeResponse toEmployeeResponse(Employee employee);
}
