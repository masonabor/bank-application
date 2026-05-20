package com.edu.bankaplication.user.core.service.impl;

import com.edu.bankaplication.user.api.dto.CreateCustomerRequest;
import com.edu.bankaplication.user.api.dto.CustomerResponse;
import com.edu.bankaplication.user.core.exception.EmptyCreateUserRequestException;
import com.edu.bankaplication.user.core.service.CustomerService;
import com.edu.bankaplication.user.persistance.CustomerRepository;
import com.edu.bankaplication.user.persistance.IdentityUserRepository;
import com.edu.bankaplication.user.persistance.entity.IdentityUser;
import com.edu.bankaplication.user.persistance.entity.customer.Customer;
import com.edu.bankaplication.user.shared.enums.Status;
import com.edu.bankaplication.user.shared.mapper.CustomerDtoMapper;
import com.edu.bankaplication.user.shared.mapper.IdentityUserDtoMapper;
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
    public CustomerResponse createUser(CreateCustomerRequest request) {
        if (request == null)
            throw new EmptyCreateUserRequestException();

        IdentityUser identityUser = identityUserDtoMapper.toIdentityIUser(request);
        Customer customer = customerDtoMapper.toCustomer(request);
        customer.setIdentityUser(identityUser);
        customer.setStatus(Status.INACTIVE);
        customerRepository.save(customer);
        return customerDtoMapper.toResponse(customer);
    }

    @Override
    public void deleteUser(Long id) {

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
