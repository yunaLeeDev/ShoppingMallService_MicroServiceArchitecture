package com.example.review.vo;

import lombok.Data;

import java.util.Date;

@Data
public class RequestReview {
    private String productId;
    private String reviewId;
    private String name;
    private String title;
    private String content;
    private int likeSum;
}
