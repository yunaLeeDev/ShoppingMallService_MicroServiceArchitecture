package com.example.review.controller;

import com.example.review.dto.ReviewDto;
import com.example.review.jpa.ReviewEntity;
import com.example.review.service.ReviewService;
import com.example.review.vo.RequestReview;
import com.example.review.vo.ResponseReview;
import lombok.RequiredArgsConstructor;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

@RestController
//@Controller
@RequestMapping("/review-service")
//@RequiredArgsConstructor
public class ReviewController {

    private Environment env;
    ReviewService service;

    @Autowired
    public ReviewController(Environment env, ReviewService reviewService) {
        this.env = env;
        this.service = reviewService;
    }

    @PostMapping("/review/add")
    public ResponseEntity<ResponseReview> createReview(@RequestBody RequestReview review){
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        ReviewDto reviewDto = mapper.map(review, ReviewDto.class);
        service.createReview(reviewDto);
        ResponseReview returnValue = mapper.map(reviewDto, ResponseReview.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
    }

//    @GetMapping ("/review/{productId}")
//    public ResponseEntity<ResponseReview> getAllByProductId(@PathVariable("productId") String productId){
//        List<ReviewDto> reviewDto = service.getReviewByProductId(productId);
//        ResponseReview result = new ModelMapper().map(reviewDto, ResponseReview.class);
//        return ResponseEntity.status(HttpStatus.OK).body(result);
//    }

    @GetMapping ("/review/{productId}")
    public ResponseEntity<List<ResponseReview>> getAllByProductId(@PathVariable("productId") String productId){
        Iterable<ReviewEntity> reviewList = service.getReviewByProductId(productId);
        List<ResponseReview> result = new ArrayList<>();
        reviewList.forEach(v->{
            result.add(new ModelMapper().map(v, ResponseReview.class));
        });
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @DeleteMapping("/review/delete/{reviewId}")
    public String deleteReview(@PathVariable("reviewId") String reviewId){
        ReviewDto reviewDto = service.getReviewByReviewId(reviewId);
        service.deleteReview(reviewDto);
        return "Delete review method is called";
    }

//    @GetMapping("/view")
//    public String test(Model model){
//        model.addAttribute("review", service.reviewList());
//        return "/main";
//    }

    @GetMapping("/main")
    public String mainINF(){
        return "/main";
    }
}