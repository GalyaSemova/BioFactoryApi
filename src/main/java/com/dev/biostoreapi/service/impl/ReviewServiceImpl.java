package com.dev.biostoreapi.service.impl;

import com.dev.biostoreapi.config.LocalDateProvider;
import com.dev.biostoreapi.exceptions.DuplicatedReviewException;
import com.dev.biostoreapi.exceptions.ProductNotFoundException;
import com.dev.biostoreapi.model.dto.ReviewAddDTO;
import com.dev.biostoreapi.model.entity.ProductEntity;
import com.dev.biostoreapi.model.entity.Review;
import com.dev.biostoreapi.model.entity.UserEntity;
import com.dev.biostoreapi.model.views.ReviewView;
import com.dev.biostoreapi.repository.ProductRepository;
import com.dev.biostoreapi.repository.ReviewRepository;
import com.dev.biostoreapi.service.ReviewService;
import com.dev.biostoreapi.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ModelMapper modelMapper;
    private final LocalDateProvider localDateProvider;
    private final UserService userService;
    private final ProductRepository productRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository, ModelMapper modelMapper, LocalDateProvider localDateProvider, UserService userService, ProductRepository productRepository) {
        this.reviewRepository = reviewRepository;
        this.modelMapper = modelMapper;
        this.localDateProvider = localDateProvider;
        this.userService = userService;
        this.productRepository = productRepository;
    }

    @Override
    public ReviewView addReview(ReviewAddDTO reviewAddDTO, UserEntity writer, Long productId) {
//        TODO test that endpoint and make it return ReviewViewModel, not DTO
        ProductEntity productEntity = productRepository.findById(productId)
                .orElseThrow(()-> new ProductNotFoundException(productId));

        Review newReview = modelMapper.map(reviewAddDTO, Review.class);
        newReview.setWriter(writer);
        newReview.setReviewDate(localDateProvider.dateTime());
        newReview.setProduct(productEntity);

        if (!reviewRepository.existsByWriterAndProduct(newReview.getWriter(), newReview.getProduct())) {
            reviewRepository.save(newReview);
        } else {
            throw new DuplicatedReviewException("Review already exists for this writer and product");
        }
        ReviewView review = modelMapper.map(newReview, ReviewView.class);
        review.setWriterUsername(writer.getUsername());
        return review;
    }

    @Override
    public List<ReviewView> getAllReviewsByProductId(Long productId) {


        return reviewRepository.findAllByProduct_Id(productId)
                .stream()
                .map(reviewEntity -> modelMapper.map(reviewEntity, ReviewView.class))
                .collect(Collectors.toList());
    }
}
