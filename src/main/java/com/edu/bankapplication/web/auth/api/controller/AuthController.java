package com.edu.bankapplication.web.auth.api.controller;

import com.edu.bankapplication.user.api.dto.CustomerResponse;
import com.edu.bankapplication.web.auth.api.dto.RegisterCustomerRequest;
import com.edu.bankapplication.web.auth.core.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register-customer")
    public ResponseEntity<@NonNull CustomerResponse> registerCustomer(@Valid @RequestBody RegisterCustomerRequest request) {
        CustomerResponse response = authService.registerCustomer(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
}
