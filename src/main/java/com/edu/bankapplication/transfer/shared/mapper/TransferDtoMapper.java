package com.edu.bankapplication.transfer.shared.mapper;

import com.edu.bankapplication.account.shared.mapper.PostingDtoMapper;
import com.edu.bankapplication.transfer.api.dto.TransferEntry;
import com.edu.bankapplication.transfer.api.dto.TransferResponse;
import com.edu.bankapplication.transfer.persistence.entity.Transfer;
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
