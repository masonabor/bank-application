package com.edu.bankaplication.account.shared.mapper;

import com.edu.bankaplication.account.api.dto.PostingEntry;
import com.edu.bankaplication.account.api.dto.PostingResponse;
import com.edu.bankaplication.account.persistence.entity.Posting;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PostingDtoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Posting toPosting(PostingEntry entry);

    @Mapping(target = "accountId", source = "account.id")
    PostingResponse toPostingResponse(Posting posting);
}
