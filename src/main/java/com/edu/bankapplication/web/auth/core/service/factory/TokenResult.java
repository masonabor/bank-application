package com.edu.bankapplication.web.auth.core.service.factory;

import com.edu.bankapplication.web.auth.persistance.entity.Token;

public record TokenResult<T extends Token<?>>(
        String token,
        T tokenEntity
) {

    public static <T extends Token<?>> TokenResult<T> of(String token, T tokenEntity) {
        return new TokenResult<>(token, tokenEntity);
    }
}
