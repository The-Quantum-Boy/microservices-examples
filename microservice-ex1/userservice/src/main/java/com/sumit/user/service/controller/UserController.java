package com.sumit.user.service.controller;

import com.sumit.user.service.entities.User;
import com.sumit.user.service.services.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user){
        User user1= userService.saveuser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }
    int retryCount=0;

    @GetMapping("/get/{userId}")
//    @CircuitBreaker(name="ratingHotelBreaker",fallbackMethod = "ratingHotelFallback")
//    @Retry(name = "ratingHotelService", fallbackMethod = "ratingHotelFallback")
    @RateLimiter(name = "userRateLimiter",fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<User> getSingleUser(@PathVariable String userId){
        System.out.println("retry count -> "+retryCount);
        retryCount++;
        User user= userService.getUser(userId);
        return ResponseEntity.ok(user);
    }

    //fallback method for circuit breaker
    public ResponseEntity<User> ratingHotelFallback(String userId,Exception ex){
//        System.out.println("fallback is executed because service is down "+ex.getMessage());
        User user=User.builder()
                .userId("23434556")
                .email("dummy@gamil.com")
                .name("dummy")
                .about("This user is crated dummy because some service is down ")
                .build();
        return new ResponseEntity<>(user,HttpStatus.OK);
    }


    @GetMapping("/getall")
    public ResponseEntity<List<User>> getAllUser(){
        List<User> allUser=userService.getAllUser();
        return ResponseEntity.ok(allUser);
    }
}
