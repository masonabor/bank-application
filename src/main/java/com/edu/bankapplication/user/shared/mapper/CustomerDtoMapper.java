package com.edu.bankapplication.user.shared.mapper;

import com.edu.bankapplication.account.shared.mapper.AccountDtoMapper;
import com.edu.bankapplication.user.api.dto.CreateCustomerRequest;
import com.edu.bankapplication.user.api.dto.CustomerResponse;
import com.edu.bankapplication.web.auth.api.dto.RegisterCustomerRequest;
import com.edu.bankapplication.user.persistance.entity.customer.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        componentModel = "spring",
        uses = {AccountDtoMapper.class}
)
public interface CustomerDtoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "identityUser", ignore = true)
    @Mapping(target = "addressInfo", ignore = true)
    @Mapping(target = "accounts", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "version", ignore = true)
    Customer toCustomer(CreateCustomerRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "identityUser", ignore = true)
    @Mapping(target = "addressInfo", ignore = true)
    @Mapping(target = "accounts", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "version", ignore = true)
    Customer toCustomer(RegisterCustomerRequest request);

    @Mapping(target = "email", source = "identityUser.email")
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "phoneNumber", source = "identityUser.phoneNumber")
    @Mapping(target = "alternativePhoneNumber", source = "identityUser.alternativePhoneNumber")
    @Mapping(target = "status", source = "identityUser.status")
    @Mapping(target = "accounts", source = "accounts")
    CustomerResponse toResponse(Customer customer);
}
