package com.edu.bankapplication.web.auth.core.service.impl;

import com.edu.bankapplication.user.persistance.entity.IdentityUser;
import com.edu.bankapplication.web.auth.core.exception.RefreshTokenNotFoundException;
import com.edu.bankapplication.web.auth.core.service.RefreshTokenService;
import com.edu.bankapplication.web.auth.core.service.factory.TokenFactory;
import com.edu.bankapplication.web.auth.core.service.factory.TokenResult;
import com.edu.bankapplication.web.auth.persistance.entity.RefreshToken;
import com.edu.bankapplication.web.auth.persistance.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED)
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {
    private final TokenFactory<RefreshToken> refreshTokenFactory;
    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    public IdentityUser findUserByRefreshTokenHash(String tokenHash) {
        RefreshToken token = refreshTokenRepository.findByTokenHash(tokenHash)
                .orElseThrow(() -> new RefreshTokenNotFoundException(tokenHash));
        return token.getUser();
    }

    @Override
    public String createRefreshToken(IdentityUser user) {
        TokenResult<RefreshToken> tokenResult = refreshTokenFactory.create(user);
        refreshTokenRepository.save(tokenResult.tokenEntity());
        return tokenResult.token();
    }

    @Override
    public void deleteByUserIfExists(IdentityUser user) {
        if (refreshTokenRepository.existsByUser(user)) {
            refreshTokenRepository.deleteByUser(user);
        }
    }
}
