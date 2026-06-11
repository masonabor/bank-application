package com.edu.bankapplication.user.shared.mapper;

import com.edu.bankapplication.user.api.dto.CreateCustomerRequest;
import com.edu.bankapplication.web.auth.api.dto.RegisterCustomerRequest;
import com.edu.bankapplication.user.persistance.entity.IdentityUser;
import com.edu.bankapplication.web.auth.api.dto.RegisterEmployeeRequest;
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
    @Mapping(target = "updatedAt", ignore = true)
    IdentityUser toIdentityUser(RegisterCustomerRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "email", source = "email")
    @Mapping(target = "passwordHash", ignore = true)
    @Mapping(target = "phoneNumber", source = "phoneNumber")
    @Mapping(target = "alternativePhoneNumber", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    IdentityUser toIdentityUser(RegisterEmployeeRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "email", source = "email")
    @Mapping(target = "passwordHash", ignore = true)
    @Mapping(target = "phoneNumber", source = "phoneNumber")
    @Mapping(target = "alternativePhoneNumber", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    IdentityUser toIdentityUser(CreateCustomerRequest request);
}
