package com.example.catologservice.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseReview {
    private String productId;
    private String reviewId;
    private String name;
    private String title;
    private String content;
    private int likeSum;
    private Date createdAt;
}
