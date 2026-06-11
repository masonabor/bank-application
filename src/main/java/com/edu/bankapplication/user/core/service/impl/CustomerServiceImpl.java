package com.edu.bankapplication.user.core.service.impl;

import com.edu.bankapplication.user.api.dto.CustomerResponse;
import com.edu.bankapplication.web.auth.api.dto.RegisterCustomerRequest;
import com.edu.bankapplication.user.core.exception.EmptyCreateUserRequestException;
import com.edu.bankapplication.user.core.service.CustomerService;
import com.edu.bankapplication.user.persistance.CustomerRepository;
import com.edu.bankapplication.user.persistance.IdentityUserRepository;
import com.edu.bankapplication.user.persistance.entity.IdentityUser;
import com.edu.bankapplication.user.persistance.entity.customer.Customer;
import com.edu.bankapplication.user.shared.enums.Status;
import com.edu.bankapplication.user.shared.mapper.CustomerDtoMapper;
import com.edu.bankapplication.user.shared.mapper.IdentityUserDtoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final IdentityUserRepository identityUserRepository;
    private final IdentityUserDtoMapper identityUserDtoMapper;
    private final CustomerDtoMapper customerDtoMapper;

    @Override
    @Transactional
    public CustomerResponse createCustomer(RegisterCustomerRequest request) {
        if (request == null)
            throw new EmptyCreateUserRequestException();

        IdentityUser identityUser = identityUserDtoMapper.toIdentityUser(request);
        identityUser.setStatus(Status.INACTIVATED);
        Customer customer = customerDtoMapper.toCustomer(request);
        customer.setIdentityUser(identityUser);
        customerRepository.save(customer);
        return customerDtoMapper.toResponse(customer);
    }

    @Override
    public void deleteCustomer(Long id) {

    }

    @Override
    public boolean existsById(Long id) {
        return customerRepository.existsById(id);
    }

    @Override
    public CustomerResponse findById(Long id) {
        return null;
    }
}
