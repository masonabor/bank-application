package com.edu.bankapplication.web.auth.api.dto;

public record LoginRequest(
        String email,
        String password
) {}
