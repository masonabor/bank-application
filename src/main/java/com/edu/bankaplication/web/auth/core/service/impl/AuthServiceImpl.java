package com.edu.bankaplication.web.auth.core.service.impl;

import com.edu.bankaplication.internalService.notification.NotificationService;
import com.edu.bankaplication.internalService.notification.dto.NotificationMessage;
import com.edu.bankaplication.user.api.dto.RegisterCustomerRequest;
import com.edu.bankaplication.user.core.exception.EmptyCreateUserRequestException;
import com.edu.bankaplication.user.core.service.CustomerService;
import com.edu.bankaplication.user.shared.mapper.CustomerDtoMapper;
import com.edu.bankaplication.user.shared.mapper.IdentityUserDtoMapper;
import com.edu.bankaplication.web.auth.core.service.AuthService;
import com.edu.bankaplication.web.auth.core.service.factory.TokenFactory;
import com.edu.bankaplication.web.auth.core.service.factory.TokenResult;
import com.edu.bankaplication.web.auth.persistance.entity.RefreshToken;
import com.edu.bankaplication.web.auth.persistance.entity.ActivationToken;
import com.edu.bankaplication.web.auth.persistance.repository.ActivationTokenRepository;
import com.edu.bankaplication.web.auth.persistance.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final TokenFactory<RefreshToken> refreshTokenFactory;
    private final TokenFactory<ActivationToken> activationTokenFactory;

    private final CustomerService customerService;
    private final NotificationService notificationService;

    private final ActivationTokenRepository activationTokenRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    private final IdentityUserDtoMapper identityUserDtoMapper;
    private final CustomerDtoMapper customerDtoMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void register(RegisterCustomerRequest request) {
        if (request == null)
            throw new EmptyCreateUserRequestException();

        var identityUser = identityUserDtoMapper.toIdentityUser(request);

        TokenResult<ActivationToken> tokenResult = activationTokenFactory.create(identityUser);

        customerService.createCustomer(request);
        activationTokenRepository.save(tokenResult.tokenEntity());

        notificationService.sendMessage(
                NotificationMessage.builder()
                        .message(tokenResult.token())
                        .build());
    }

    @Override
    public void activate(String activationToken) {

    }

    @Override
    public void login(String email, String password) {

    }
}
