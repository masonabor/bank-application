package com.edu.bankaplication.web.auth.core.service.factory;

import com.edu.bankaplication.web.auth.persistance.entity.Token;

public record TokenResult<T>(
        String token,
        T tokenEntity
) {
}
