package com.edu.bankaplication.web.auth.core.service;

import com.edu.bankaplication.user.api.dto.RegisterCustomerRequest;

public interface AuthService {

    void register(RegisterCustomerRequest request);
    void login(String email, String password);
    void activate(String activationToken);

}
