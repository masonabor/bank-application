package com.edu.bankaplication.web.auth.core.service.factory;

import com.edu.bankaplication.user.persistance.entity.IdentityUser;
import com.edu.bankaplication.web.auth.persistance.entity.Token;

public interface TokenFactory<T extends Token<?>> {
    TokenResult<T> create(IdentityUser user);
}
