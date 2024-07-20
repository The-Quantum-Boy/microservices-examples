package com.sumit.user.service;

import com.sumit.user.service.entities.Rating;
import com.sumit.user.service.external.services.RatingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

@SpringBootTest
class UserServiceApplicationTests {

	@Autowired
	private RatingService ratingService;

	@Test
	void contextLoads() {
	}

	@Test
	void createRating(){
		Rating rating=Rating.builder()
				.userId("")
				.hotelId("")
				.feedback("this is sceond feign test")
				.build();

//		Rating savedRating = ratingService.createRating(rating);

		ResponseEntity<Rating> ratingResponseEntity=ratingService.createRating(rating);
		System.out.println("new rating created.... "+ratingResponseEntity.getStatusCode());
	}

}
