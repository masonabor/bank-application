package com.edu.bankapplication.user.persistance;

import com.edu.bankapplication.user.persistance.entity.employee.Employee;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<@NonNull Employee, @NonNull Long> {

}
