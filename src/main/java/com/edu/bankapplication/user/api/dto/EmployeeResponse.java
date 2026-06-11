package com.edu.bankapplication.user.api.dto;

import com.edu.bankapplication.user.shared.enums.Gender;
import com.edu.bankapplication.user.shared.enums.Status;

public record EmployeeResponse(
        String firstName,
        String lastName,
        String middleName,
        String email,
        String password,
        Gender gender,
        String phoneNumber,
        String alternativePhoneNumber,
        Status status
) {}
