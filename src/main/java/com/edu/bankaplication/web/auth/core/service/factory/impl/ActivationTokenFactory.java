package com.edu.bankaplication.web.auth.core.service.factory.impl;

import com.edu.bankaplication.user.core.exception.EmptyUserException;
import com.edu.bankaplication.user.persistance.entity.IdentityUser;
import com.edu.bankaplication.web.auth.core.service.factory.TokenFactory;
import com.edu.bankaplication.web.auth.core.service.factory.TokenResult;
import com.edu.bankaplication.web.auth.persistance.entity.ActivationToken;
import com.edu.bankaplication.web.auth.shared.config.TokenConfigurationProperties;
import com.edu.bankaplication.web.auth.shared.utils.SecureTokenGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Component("verificationTokenFactory")
@RequiredArgsConstructor
public class ActivationTokenFactory implements TokenFactory<ActivationToken> {

    private final PasswordEncoder passwordEncoder;
    private final TokenConfigurationProperties tokenConfigurationProperties;
    private final SecureTokenGenerator secureTokenGenerator;

    @Override
    public TokenResult<ActivationToken> create(IdentityUser user) {
        if (user == null)
            throw new EmptyUserException();

        String token = secureTokenGenerator.generateToken();
        String hashedToken = passwordEncoder.encode(token);
        Instant expiresAt = Instant.now().plus(tokenConfigurationProperties.verificationTokenExpireTime(), ChronoUnit.MINUTES);

        ActivationToken refreshToken = ActivationToken.builder()
                .user(user)
                .verificationTokenHash(hashedToken)
                .expiresAt(expiresAt)
                .build();

        return TokenResult.of(token, refreshToken);
    }
}
