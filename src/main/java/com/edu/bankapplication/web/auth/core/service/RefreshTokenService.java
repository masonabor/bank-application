package com.edu.bankapplication.web.auth.core.service;

import com.edu.bankapplication.user.persistance.entity.IdentityUser;

public interface RefreshTokenService {
    IdentityUser findUserByRefreshTokenHash(String tokenHash);
    String createRefreshToken(IdentityUser user);
    void deleteByUserIfExists(IdentityUser user);
}
