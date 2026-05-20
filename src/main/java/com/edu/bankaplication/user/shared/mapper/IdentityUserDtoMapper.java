package com.edu.bankaplication.user.shared.mapper;

import com.edu.bankaplication.user.api.dto.CreateCustomerRequest;
import com.edu.bankaplication.user.persistance.entity.IdentityUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IdentityUserDtoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "email", source = "email")
    @Mapping(target = "passwordHash", ignore = true)
    @Mapping(target = "phoneNumber", source = "phoneNumber")
    @Mapping(target = "alternativePhoneNumber", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updateAt", ignore = true)
    IdentityUser toIdentityIUser(CreateCustomerRequest request);
}
