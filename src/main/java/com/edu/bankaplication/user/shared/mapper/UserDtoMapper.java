package com.edu.bankaplication.user.shared.mapper;

import com.edu.bankaplication.account.shared.mapper.AccountDtoMapper;
import com.edu.bankaplication.user.api.dto.CreateUserRequest;
import com.edu.bankaplication.user.api.dto.UserResponse;
import com.edu.bankaplication.user.persistance.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        componentModel = "spring",
        uses = {AccountDtoMapper.class}
)
public interface UserDtoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "addressInfo", ignore = true)
    @Mapping(target = "accounts", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    User toUser(CreateUserRequest request);

    UserResponse toResponse(User user);
}
