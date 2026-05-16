package com.edu.bankaplication.user.api.dto;

import com.edu.bankaplication.user.shared.enums.Gender;

public record CreateUserRequest(
        String firstName,
        String lastName,
        String middleName,
        String email,
        String password,
        Gender gender,
        String phoneNumber
) {}
