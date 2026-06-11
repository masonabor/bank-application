package com.edu.bankapplication.web.auth.api.dto;

import lombok.Builder;

@Builder
public record AccessTokenResponse(
        String accessToken
) {
}
