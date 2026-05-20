package com.edu.bankaplication.account.shared.mapper;

import com.edu.bankaplication.account.api.dto.AccountResponse;
import com.edu.bankaplication.account.api.dto.CreateAccountRequest;
import com.edu.bankaplication.account.persistence.entity.Account;
import com.edu.bankaplication.user.persistance.entity.customer.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountDtoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "balance", ignore = true)
    @Mapping(target = "user", source = "user")
    @Mapping(target = "postings", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "dueTo", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Account toAccount(CreateAccountRequest createAccountRequest, Customer customer);

    @Mapping(target = "userId", source = "user.id")
    AccountResponse toAccountResponse(Account account);

}
