package com.edu.bankaplication.account.core.service.impl;

import com.edu.bankaplication.account.api.dto.PostingEntry;
import com.edu.bankaplication.account.api.dto.PostingResponse;
import com.edu.bankaplication.account.core.exception.EmptyPostingEntryException;
import com.edu.bankaplication.account.core.service.PostingService;
import com.edu.bankaplication.account.persistence.PostingRepository;
import com.edu.bankaplication.account.persistence.entity.Posting;
import com.edu.bankaplication.account.shared.mapper.PostingDtoMapper;
import com.edu.bankaplication.transaction.core.exception.EmptyTransferEntryException;
import com.edu.bankaplication.transaction.persistence.TransferRepository;
import com.edu.bankaplication.transaction.persistence.entity.Transfer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostingServiceImpl implements PostingService {
    private final PostingRepository postingRepository;
    private final TransferRepository transferRepository;
    private final PostingDtoMapper postingDtoMapper;

    @Override
    public PostingResponse createPosting(PostingEntry entry) {
        if (entry == null)
            throw new EmptyPostingEntryException();

        return new PostingResponse(null, null, null, null);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Set<PostingResponse> createPostings(Set<PostingEntry> postingEntries, Transfer transfer) {
        if (postingEntries == null || postingEntries.size() != 2)
            throw new EmptyPostingEntryException();
        if (transfer == null)
            throw new EmptyTransferEntryException();

        Set<Posting> postings = postingEntries.stream()
                        .map(postingDtoMapper::toPosting)
                        .collect(Collectors.toSet());

        postingRepository.saveAll(postings);
        transfer.setPostings(postings);
        transferRepository.save(transfer);

        return postings.stream()
                .map(postingDtoMapper::toPostingResponse)
                .collect(Collectors.toSet());
    }
}
