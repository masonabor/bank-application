package com.edu.bankaplication.web.auth.core.service.factory.impl;

import com.edu.bankaplication.user.persistance.entity.IdentityUser;
import com.edu.bankaplication.web.auth.core.service.factory.TokenFactory;
import com.edu.bankaplication.web.auth.core.service.factory.TokenResult;
import com.edu.bankaplication.web.auth.persistance.entity.VerificationToken;
import org.springframework.stereotype.Component;

@Component("verificationTokenFactory")
public class VerificationTokenFactory implements TokenFactory<VerificationToken> {

    @Override
    public TokenResult<VerificationToken> create(IdentityUser user) {
        return null;
    }
}
