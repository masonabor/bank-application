package com.edu.bankaplication.user.core.service;

import com.edu.bankaplication.user.api.dto.CreateUserRequest;
import com.edu.bankaplication.user.api.dto.UserResponse;

public interface UserService {
    UserResponse createUser(CreateUserRequest request);
    void deleteUser(Long id);
}
