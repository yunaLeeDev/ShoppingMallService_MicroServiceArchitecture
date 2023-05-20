package com.example.review;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@MapperScan(basePackages = "com.example.review.mapper")
public class ReviewApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReviewApplication.class, args);
	}


}
