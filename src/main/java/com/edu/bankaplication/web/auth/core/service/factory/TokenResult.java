package com.edu.bankaplication.web.auth.core.service.factory;

import com.edu.bankaplication.web.auth.persistance.entity.Token;

public record TokenResult<T extends Token<?>>(
        String token,
        T tokenEntity
) {

    public static <T extends Token<?>> TokenResult<T> of(String token, T tokenEntity) {
        return new TokenResult<>(token, tokenEntity);
    }
}
