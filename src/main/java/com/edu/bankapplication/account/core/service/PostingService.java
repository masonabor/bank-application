package com.edu.bankapplication.account.core.service;

import com.edu.bankapplication.account.api.dto.PostingEntry;
import com.edu.bankapplication.account.api.dto.PostingResponse;
import com.edu.bankapplication.transfer.persistence.entity.Transfer;

import java.util.Set;

public interface PostingService {
    PostingResponse createPosting(PostingEntry entry);
    Set<PostingResponse> createPostings(Set<PostingEntry> postingEntries, Transfer transfer);
}
