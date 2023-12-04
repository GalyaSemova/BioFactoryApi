package com.dev.biostoreapi.service;

import com.dev.biostoreapi.model.dto.ReviewAddDTO;
import com.dev.biostoreapi.model.entity.UserEntity;
import com.dev.biostoreapi.model.views.ReviewView;

import java.util.List;

public interface ReviewService {
    ReviewView addReview(ReviewAddDTO reviewAddDTO, UserEntity writer, Long productId);

    List<ReviewView> getAllReviewsByProductId(Long productId);
}
