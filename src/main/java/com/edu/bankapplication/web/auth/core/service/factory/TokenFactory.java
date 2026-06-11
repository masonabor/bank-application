package com.edu.bankapplication.web.auth.core.service.factory;

import com.edu.bankapplication.user.persistance.entity.IdentityUser;
import com.edu.bankapplication.web.auth.persistance.entity.Token;

public interface TokenFactory<T extends Token<?>> {
    TokenResult<T> create(IdentityUser user);
}
