package com.edu.bankapplication.web.auth.core.service;

import com.edu.bankapplication.user.persistance.entity.IdentityUser;

public interface JwtService {
    String createAccessToken(IdentityUser user);
}
