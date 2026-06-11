package com.edu.bankapplication.web.auth.core.service.factory.impl;

import com.edu.bankapplication.user.core.exception.EmptyUserException;
import com.edu.bankapplication.user.persistance.entity.IdentityUser;
import com.edu.bankapplication.web.auth.core.service.factory.TokenFactory;
import com.edu.bankapplication.web.auth.core.service.factory.TokenResult;
import com.edu.bankapplication.web.auth.persistance.entity.RefreshToken;
import com.edu.bankapplication.web.auth.shared.config.TokenConfigurationProperties;
import com.edu.bankapplication.web.auth.shared.utils.SecureTokenGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Component("refreshTokenFactory")
@RequiredArgsConstructor
public class RefreshTokenFactory implements TokenFactory<RefreshToken> {

    private final TokenConfigurationProperties tokenConfigurationProperties;
    private final PasswordEncoder passwordEncoder;
    private final SecureTokenGenerator secureTokenGenerator;

    @Override
    public TokenResult<RefreshToken> create(IdentityUser user) {
        if (user == null)
            throw new EmptyUserException();

        String token = secureTokenGenerator.generateToken();
        String hashedToken = passwordEncoder.encode(token);
        Instant expiresAt = Instant.now().plus(tokenConfigurationProperties.refreshTokenExpireTime(), ChronoUnit.DAYS);

        RefreshToken refreshToken = RefreshToken.builder()
                .user(user)
                .refreshTokenHash(hashedToken)
                .expiresAt(expiresAt)
                .build();

        return TokenResult.of(token, refreshToken);
    }
}
