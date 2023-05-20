package com.example.review.jpa;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "review")
public class ReviewEntity implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String productId;
    @Column(nullable = false, length = 120, unique = true)
    private String reviewId;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private Integer likeSum;

    @Column(nullable = false, updatable = false, insertable = false)
    @ColumnDefault(value = "CURRENT_TIMESTAMP")
    private Date createdAt;
}
