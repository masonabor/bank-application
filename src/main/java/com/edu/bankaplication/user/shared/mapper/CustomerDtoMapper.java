package com.edu.bankaplication.user.shared.mapper;

import com.edu.bankaplication.account.shared.mapper.AccountDtoMapper;
import com.edu.bankaplication.user.api.dto.CreateCustomerRequest;
import com.edu.bankaplication.user.api.dto.CustomerResponse;
import com.edu.bankaplication.user.persistance.entity.customer.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        componentModel = "spring",
        uses = {AccountDtoMapper.class}
)
public interface CustomerDtoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "addressInfo", ignore = true)
    @Mapping(target = "accounts", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Customer toCustomer(CreateCustomerRequest request);

    CustomerResponse toResponse(Customer customer);
}
