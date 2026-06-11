package com.edu.bankapplication.user.api.dto;

import com.edu.bankapplication.user.shared.enums.Gender;

public record CreateCustomerRequest(
        String firstName,
        String lastName,
        String middleName,
        String email,
        String password,
        Gender gender,
        String phoneNumber
) {}
