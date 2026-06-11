package com.edu.bankapplication.user.api.dto;

import com.edu.bankapplication.account.api.dto.AccountResponse;
import com.edu.bankapplication.user.shared.enums.Gender;
import com.edu.bankapplication.user.shared.enums.Status;

import java.util.Set;

public record CustomerResponse(
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
