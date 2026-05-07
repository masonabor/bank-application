package com.edu.bankaplication.user.core.service.impl;

import com.edu.bankaplication.user.api.dto.CreateUserRequest;
import com.edu.bankaplication.user.api.dto.UserResponse;
import com.edu.bankaplication.user.core.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Override
    public UserResponse createUser(CreateUserRequest request) {
        return null;
    }

    @Override
    public void deleteUser(Long id) {

    }
}
