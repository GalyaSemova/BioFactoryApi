package com.dev.biostoreapi.model.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public class ReviewAddDTO {


    private String reviewComment;
    @Min(value = 1, message = "The minimum value is 1")
    @Max(value = 5, message = "The maximum value is 5")
    private int rating; // 1-5 stars

    public String getReviewComment() {
        return reviewComment;
    }

    public void setReviewComment(String reviewComment) {
        this.reviewComment = reviewComment;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

}
