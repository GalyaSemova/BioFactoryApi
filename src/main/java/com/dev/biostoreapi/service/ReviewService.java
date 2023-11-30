package com.dev.biostoreapi.service;

import com.dev.biostoreapi.model.dto.ReviewAddDTO;
import com.dev.biostoreapi.model.entity.UserEntity;
import com.dev.biostoreapi.model.views.ReviewView;

public interface ReviewService {
    ReviewView addReview(ReviewAddDTO reviewAddDTO, UserEntity writer, Long productId);
}
