package com.edu.bankaplication.account.core.service;

import com.edu.bankaplication.account.api.dto.CreatePostingRequest;
import com.edu.bankaplication.account.api.dto.PostingResponse;

public interface PostingService {
    PostingResponse createPosting(CreatePostingRequest request);
}
