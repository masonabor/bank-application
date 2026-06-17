package com.edu.bankapplication.web.auth.core.service.impl;

import com.edu.bankapplication.internalService.notification.NotificationService;
import com.edu.bankapplication.internalService.notification.dto.NotificationMessage;
import com.edu.bankapplication.user.api.dto.CustomerResponse;
import com.edu.bankapplication.user.api.dto.EmployeeResponse;
import com.edu.bankapplication.user.core.exception.UserNotFoundException;
import com.edu.bankapplication.user.core.exception.UserStatusException;
import com.edu.bankapplication.user.core.service.EmployeeService;
import com.edu.bankapplication.user.persistance.CustomerRepository;
import com.edu.bankapplication.user.persistance.EmployeeRepository;
import com.edu.bankapplication.user.shared.enums.Role;
import com.edu.bankapplication.user.shared.mapper.EmployeeDtoMapper;
import com.edu.bankapplication.web.auth.api.dto.*;
import com.edu.bankapplication.user.core.exception.EmptyCreateUserRequestException;
import com.edu.bankapplication.user.core.service.CustomerService;
import com.edu.bankapplication.user.persistance.IdentityUserRepository;
import com.edu.bankapplication.user.persistance.entity.IdentityUser;
import com.edu.bankapplication.user.shared.enums.Status;
import com.edu.bankapplication.user.shared.mapper.CustomerDtoMapper;
import com.edu.bankapplication.user.shared.mapper.IdentityUserDtoMapper;
import com.edu.bankapplication.web.auth.core.exception.*;
import com.edu.bankapplication.web.auth.core.service.AuthService;
import com.edu.bankapplication.web.auth.core.service.JwtService;
import com.edu.bankapplication.web.auth.core.service.factory.TokenFactory;
import com.edu.bankapplication.web.auth.core.service.factory.TokenResult;
import com.edu.bankapplication.web.auth.persistance.entity.RefreshToken;
import com.edu.bankapplication.web.auth.persistance.entity.ActivationToken;
import com.edu.bankapplication.web.auth.persistance.repository.ActivationTokenRepository;
import com.edu.bankapplication.web.auth.persistance.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final TokenFactory<RefreshToken> refreshTokenFactory;
    private final TokenFactory<ActivationToken> activationTokenFactory;

    private final CustomerService customerService;
    private final EmployeeService employeeService;
    private final NotificationService notificationService;
    private final JwtService jwtService;

    private final ActivationTokenRepository activationTokenRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final IdentityUserRepository identityUserRepository;
    private final CustomerRepository customerRepository;
    private final EmployeeRepository employeeRepository;

    private final IdentityUserDtoMapper identityUserDtoMapper;
    private final CustomerDtoMapper customerDtoMapper;
    private final EmployeeDtoMapper employeeDtoMapper;

    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public CustomerResponse registerCustomer(RegisterCustomerRequest request) {
        if (request == null)
            throw new EmptyCreateUserRequestException();

        if (identityUserRepository.existsByEmail(request.email()))
            throw new UserAlreadyExistsException(request.email());

        var identityUser = identityUserDtoMapper.toIdentityUser(request);
        var customer = customerDtoMapper.toCustomer(request);

        identityUser.setPasswordHash(passwordEncoder.encode(request.password()));
        identityUser.setStatus(Status.INACTIVATED);
        identityUser.setRole(Role.CUSTOMER);
        customer.setIdentityUser(identityUser);
        customerRepository.save(customer);

        TokenResult<ActivationToken> tokenResult = activationTokenFactory.create(identityUser);
        activationTokenRepository.save(tokenResult.tokenEntity());

        notificationService.sendMessage(
                NotificationMessage.builder()
                        .message(tokenResult.token())
                        .build());

        return customerDtoMapper.toResponse(customer);
    }

    @Override
    @Transactional(propagation =  Propagation.REQUIRES_NEW)
    public EmployeeResponse registerEmployee(RegisterEmployeeRequest request) {
        if (request == null)
            throw new EmptyCreateUserRequestException();

        if (identityUserRepository.existsByEmail(request.email()))
            throw new UserAlreadyExistsException(request.email());

        var identityUser = identityUserDtoMapper.toIdentityUser(request);
        var employee = employeeDtoMapper.toEmployee(request);

        identityUser.setPasswordHash(passwordEncoder.encode(request.password()));
        identityUser.setStatus(Status.INACTIVATED);
        identityUser.setRole(Role.ADMIN);

        employee.setIdentityUser(identityUser);

        TokenResult<ActivationToken> tokenResult = activationTokenFactory.create(identityUser);
        activationTokenRepository.save(tokenResult.tokenEntity());

        notificationService.sendMessage(
                NotificationMessage.builder()
                        .message(tokenResult.token())
                        .build());

        return employeeDtoMapper.toEmployeeResponse(employee);
    }

    public void register(RegisterRequest request) {

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void activate(String activationToken) {
        String activationTokenHash = passwordEncoder.encode(activationToken);
        ActivationToken token = activationTokenRepository.findByTokenHash(activationTokenHash)
                .orElseThrow(() -> new ActivationTokenNotFoundException(activationToken));

        IdentityUser user = token.getUser();
        user.setStatus(Status.ACTIVATED);

        activationTokenRepository.deleteByTokenHash(activationTokenHash);
        identityUserRepository.save(user);
    }

    @Override
    public AccessTokenResponse refreshAccessToken(String refreshToken) {
        if (refreshToken == null)
            throw new EmptyRefreshTokenException();

        String tokenHash = passwordEncoder.encode(refreshToken);
        RefreshToken token = refreshTokenRepository.findByTokenHash(tokenHash)
                .orElseThrow(() -> new RefreshTokenNotFoundException(refreshToken));

        IdentityUser user = token.getUser();
        String accessToken = jwtService.createAccessToken(user);
        return AccessTokenResponse.builder()
                .accessToken(accessToken)
                .build();
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        if (request == null)
            throw new EmptyLoginRequestException();

        IdentityUser user = identityUserRepository.findByEmail(request.email())
                .orElseThrow(() -> new UserNotFoundException(request.email()));

        if (!user.getStatus().equals(Status.ACTIVATED))
            throw new UserStatusException(user.getId());

        String passwordHash = passwordEncoder.encode(request.password());

        if (!user.getPasswordHash().equals(passwordHash))
            throw new InvalidPasswordException();

        TokenResult<RefreshToken> refreshTokenResult = refreshTokenFactory.create(user);
        refreshTokenRepository.save(refreshTokenResult.tokenEntity());

        String accessToken = jwtService.createAccessToken(user);
        String refreshToken = refreshTokenResult.token();

        return LoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
