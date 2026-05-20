package com.edu.bankaplication.web.auth.core.service.factory.impl;

import com.edu.bankaplication.user.core.exception.EmptyUserException;
import com.edu.bankaplication.user.persistance.entity.IdentityUser;
import com.edu.bankaplication.web.auth.core.service.factory.TokenFactory;
import com.edu.bankaplication.web.auth.core.service.factory.TokenResult;
import com.edu.bankaplication.web.auth.persistance.entity.RefreshToken;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component("refreshTokenFactory")
@RequiredArgsConstructor
public class RefreshTokenFactory implements TokenFactory<RefreshToken> {

    private final PasswordEncoder passwordEncoder;

    @Override
    public TokenResult<RefreshToken> create(IdentityUser user) {
        if (user == null)
            throw new EmptyUserException();

        String token = UUID.randomUUID().toString();
        String hashedToken = passwordEncoder.encode(token);

        RefreshToken refreshToken = RefreshToken.builder()
                .user(user)
                .refreshTokenHash(hashedToken)
                .build();

    }
}
