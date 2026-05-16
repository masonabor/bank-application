package com.edu.bankaplication.user.core.service.impl;

import com.edu.bankaplication.user.api.dto.CreateUserRequest;
import com.edu.bankaplication.user.api.dto.UserResponse;
import com.edu.bankaplication.user.core.exception.EmptyCreateUserRequestException;
import com.edu.bankaplication.user.core.service.UserService;
import com.edu.bankaplication.user.persistance.UserRepository;
import com.edu.bankaplication.user.persistance.entity.User;
import com.edu.bankaplication.user.shared.enums.Status;
import com.edu.bankaplication.user.shared.mapper.UserDtoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserDtoMapper userDtoMapper;

    @Override
    @Transactional
    public UserResponse createUser(CreateUserRequest request) {
        if (request == null)
            throw new EmptyCreateUserRequestException();

        User user = userDtoMapper.toUser(request);
        user.setStatus(Status.INACTIVE);
        userRepository.save(user);
        return userDtoMapper.toResponse(user);
    }

    @Override
    public void deleteUser(Long id) {

    }

    @Override
    public boolean existsById(Long id) {
        return userRepository.existsById(id);
    }

    @Override
    public UserResponse findById(Long id) {
        return null;
    }
}
