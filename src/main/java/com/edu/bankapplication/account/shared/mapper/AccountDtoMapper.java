package com.edu.bankapplication.account.shared.mapper;

import com.edu.bankapplication.account.api.dto.AccountResponse;
import com.edu.bankapplication.account.api.dto.CreateAccountRequest;
import com.edu.bankapplication.account.persistence.entity.Account;
import com.edu.bankapplication.user.persistance.entity.customer.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountDtoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "balance", ignore = true)
    @Mapping(target = "customer", source = "customer")
    @Mapping(target = "postings", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "dueTo", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Account toAccount(CreateAccountRequest createAccountRequest, Customer customer);

    @Mapping(target = "userId", source = "customer.id")
    AccountResponse toAccountResponse(Account account);

}
