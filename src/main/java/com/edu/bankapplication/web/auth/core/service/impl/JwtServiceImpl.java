package com.edu.bankapplication.web.auth.core.service.impl;

import com.edu.bankapplication.user.persistance.entity.IdentityUser;
import com.edu.bankapplication.web.auth.core.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {

    @Value("${token.access-token-expire-time}")
    private int expirationTimeMinutes;

    private final JwtEncoder jwtEncoder;

    @Override
    public String createAccessToken(IdentityUser user) {
        Instant now = Instant.now();

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("bank-auth-service")
                .subject(user.getId().toString())
                .audience(List.of("bank-api"))
                .issuedAt(now)
                .expiresAt(now.plus(Duration.ofMinutes(expirationTimeMinutes)))
                .claim("role", user.getRole())
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims))
                .getTokenValue();
    }
}
