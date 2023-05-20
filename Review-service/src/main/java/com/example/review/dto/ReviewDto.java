package com.example.review.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ReviewDto implements Serializable {
    private long id;
    private String productId;
    private String reviewId;
    private String name;
    private String title;
    private String content;
    private int likeSum;
    private Date createdAt;
}
