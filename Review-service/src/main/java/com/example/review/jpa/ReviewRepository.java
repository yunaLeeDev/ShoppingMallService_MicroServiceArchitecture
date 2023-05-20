package com.example.review.jpa;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReviewRepository extends CrudRepository<ReviewEntity, Long> {
    List<ReviewEntity> findByProductId(String productId);

//    Iterable<ReviewEntity> findAllById(String productId);

    ReviewEntity findByReviewId(String reviewId);
}
