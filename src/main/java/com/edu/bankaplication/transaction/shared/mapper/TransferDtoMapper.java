package com.edu.bankaplication.transaction.shared.mapper;

import com.edu.bankaplication.account.shared.mapper.PostingDtoMapper;
import com.edu.bankaplication.transaction.api.dto.TransferEntry;
import com.edu.bankaplication.transaction.api.dto.TransferResponse;
import com.edu.bankaplication.transaction.persistence.entity.Transfer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        componentModel = "spring",
        uses = PostingDtoMapper.class
)
public interface TransferDtoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "postings", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Transfer toTransfer(TransferEntry transferEntry);

    @Mapping(target = "transferPostings", source = "postings")
    TransferResponse toTransferResponse(Transfer transfer);
}
