package com.example.review.service;

import com.example.review.dto.ReviewDto;
import com.example.review.jpa.ReviewEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//@Service
//@RequiredArgsConstructor
//@Transactional(readOnly = true)
public interface ReviewService {
//    private final ReviewMapper reviewMapper;
//    public List<Review> reviewList() {
//        return reviewMapper.getList();
//    }

    ReviewDto createReview(ReviewDto reviewDto);
    ReviewDto deleteReview(ReviewDto reviewDto);
    ReviewDto getReviewByReviewId(String reviewId);

    Iterable<ReviewEntity> getReviewByProductId(String productId);
}
