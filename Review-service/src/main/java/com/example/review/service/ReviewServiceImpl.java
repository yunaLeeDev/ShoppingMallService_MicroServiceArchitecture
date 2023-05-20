package com.example.review.service;

import com.example.review.dto.ReviewDto;
import com.example.review.jpa.ReviewEntity;
import com.example.review.jpa.ReviewRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ReviewServiceImpl implements ReviewService{

    ReviewRepository reviewRepository;
    Environment env;
    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, Environment env){
        this.reviewRepository = reviewRepository;
        this.env = env;
    }

//    @Override
//    public Iterable<ReviewEntity> getAllReview() {
//        return reviewRepository.findAll();
//    }

    @Override
    public ReviewDto createReview(ReviewDto reviewDto) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        ReviewEntity reviewEntity = mapper.map(reviewDto, ReviewEntity.class);
        reviewRepository.save(reviewEntity);
        return null;
    }

    @Override
    public ReviewDto deleteReview(ReviewDto reviewDto) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        ReviewEntity reviewEntity = mapper.map(reviewDto, ReviewEntity.class);
        reviewRepository.delete(reviewEntity);
        return null;
    }

    @Override
    public ReviewDto getReviewByReviewId(String reviewId) {
        ReviewEntity reviewEntity = reviewRepository.findByReviewId(reviewId);
        ReviewDto reviewDto = new ModelMapper().map(reviewEntity, ReviewDto.class);
        return reviewDto;
    }

    @Override
    public Iterable<ReviewEntity> getReviewByProductId(String productId) {
//        List<ReviewDto> reviewDto = new ArrayList<>();
//        List<ReviewEntity> reviewList = reviewRepository.findByProductId(productId);
//        reviewList.forEach(v->{
//            reviewDto.add(new ModelMapper().map(v, ReviewDto.class));
//        });
        return reviewRepository.findByProductId(productId);
    }

//    @Override
//    public ReviewDto getReviewByProductId(String productId) {
//        ReviewEntity reviewEntity = reviewRepository.findByProductId(productId);
//        ReviewDto reviewDto = new ModelMapper().map(reviewEntity, ReviewDto.class);
//        return reviewDto;
//    }
}
