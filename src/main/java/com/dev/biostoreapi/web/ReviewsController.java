package com.dev.biostoreapi.web;

import com.dev.biostoreapi.exceptions.DuplicatedReviewException;
import com.dev.biostoreapi.exceptions.ProductNotFoundException;
import com.dev.biostoreapi.model.dto.ReviewAddDTO;
import com.dev.biostoreapi.model.entity.UserEntity;
import com.dev.biostoreapi.model.views.ReviewView;
import com.dev.biostoreapi.service.ProductService;
import com.dev.biostoreapi.service.ReviewService;
import com.dev.biostoreapi.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewsController {

    private final UserService userService;
    private final ReviewService reviewService;
    private final ProductService productService;

    public ReviewsController(UserService userService, ReviewService reviewService, ProductService productService) {
        this.userService = userService;
        this.reviewService = reviewService;
        this.productService = productService;
    }

//TODO implement on fe
    @PostMapping ("/{id}/add")
    public ResponseEntity<?> addReview(
            @PathVariable("id") Long productId,
            @RequestBody @Valid ReviewAddDTO reviewAddDTO,
            BindingResult bindingResult,
            @AuthenticationPrincipal UserDetails user
            ) {

        if (bindingResult.hasErrors()) {
            Map<String, String> validationErrors = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                validationErrors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(validationErrors);
        }

        try {
            UserEntity writer = userService.findByUsername(user.getUsername());
            ReviewView review = reviewService.addReview(reviewAddDTO, writer, productId);
            return ResponseEntity.status(HttpStatus.CREATED).body(review);
        } catch (DuplicatedReviewException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (ProductNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
