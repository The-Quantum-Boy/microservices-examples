package com.sumit.user.service.external.services;

import com.sumit.user.service.entities.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
@Service
@FeignClient(name = "RATING-SERVICE")
public interface RatingService {

    //get

    //post
    @PostMapping("/rating/create")
//    public Rating createRating(@RequestBody Rating values); //we can also pass data through map if we don't have the Rating class in the present service
    public ResponseEntity<Rating> createRating(@RequestBody Rating values); //we can also pass data through map if we don't have the Rating class in the present service

    //update
    @PutMapping("/rating/{ratingId}")
    public Rating updateRating(@PathVariable String ratingId);

    @DeleteMapping("/rating/{ratingId}")
    public void deleteRating(@PathVariable String ratingId);

}
