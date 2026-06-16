package com.edu.bankapplication.web.auth.shared.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Validated
@ConfigurationProperties("token")
public record TokenConfigurationProperties(

        @NotNull
        @Min(1)
        long refreshTokenExpireTime,

        @NotNull
        @Min(1)
        long verificationTokenExpireTime
) {}
