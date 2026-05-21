package com.edu.bankaplication.web.auth.api.dto;

import lombok.Builder;
import org.flywaydb.core.internal.parser.TokenType;

@Builder
public record TokenResponse(
        TokenType type,
        String token,
        long expiresAt
) {}
