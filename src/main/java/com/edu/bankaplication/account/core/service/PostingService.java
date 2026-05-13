package com.edu.bankaplication.account.core.service;

import com.edu.bankaplication.account.api.dto.PostingEntry;
import com.edu.bankaplication.account.api.dto.PostingResponse;
import com.edu.bankaplication.transaction.persistence.entity.Transfer;

import java.util.Set;

public interface PostingService {
    PostingResponse createPosting(PostingEntry entry);
    Set<PostingResponse> createPostings(Set<PostingEntry> postingEntries, Transfer transfer);
}
