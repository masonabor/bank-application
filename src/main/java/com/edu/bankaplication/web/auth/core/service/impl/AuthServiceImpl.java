package com.edu.bankaplication.web.auth.core.service.impl;

import com.edu.bankaplication.web.auth.core.service.factory.TokenFactory;
import com.edu.bankaplication.web.auth.persistance.entity.RefreshToken;
import com.edu.bankaplication.web.auth.persistance.entity.VerificationToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl {

    private final TokenFactory<RefreshToken> refreshTokenFactory;
    private final TokenFactory<VerificationToken> verificationTokenFactory;

}
