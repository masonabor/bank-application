package com.edu.bankaplication.user.api.dto;

import com.edu.bankaplication.account.api.dto.AccountResponse;
import com.edu.bankaplication.user.shared.enums.Gender;
import com.edu.bankaplication.user.shared.enums.Status;

import java.util.Set;

public record UserResponse(
        String firstName,
        String lastName,
        String middleName,
        String email,
        String password,
        Gender gender,
        String phoneNumber,
        String alternativePhoneNumber,
        // address info dto
        Set<AccountResponse> accounts,
        Status status
) {}
