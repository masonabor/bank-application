package com.edu.bankapplication.web.auth.api.dto;

import com.edu.bankapplication.user.shared.enums.Gender;

public record RegisterCustomerRequest(
        String firstName,
        String lastName,
        String middleName,
        String email,
        String password,
        Gender gender,
        String phoneNumber
) {}
