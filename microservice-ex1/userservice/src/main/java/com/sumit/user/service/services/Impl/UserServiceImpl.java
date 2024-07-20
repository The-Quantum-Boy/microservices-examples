package com.sumit.user.service.services.Impl;

import com.sumit.user.service.entities.Hotel;
import com.sumit.user.service.entities.Rating;
import com.sumit.user.service.entities.User;
import com.sumit.user.service.exceptions.ResourceNotFoundException;
import com.sumit.user.service.external.services.HotelService;
import com.sumit.user.service.repositories.UserRepository;
import com.sumit.user.service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;


    @Override
    public User saveuser(User user) {
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
        User user=userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("user with given id not found on server "+userId));
        //fetch rating of above user from rating service
        //http://localhost:8083/rating/userid/35d34df7-b4b3-444a-b3f3-6c9d6bb5cefd

       Rating[] ratings = restTemplate.getForObject("http://RATING-SERVICE/rating/userid/"+user.getUserId(), Rating[].class);

        List<Rating> list = Arrays.stream(ratings).toList();

        List<Rating> ratingList = list.stream().map(rating -> {
            //api call to hotel service hotel
//            ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);

            Hotel hotel = hotelService.getHotel(rating.getHotelId());
            //set hotel to rating
            rating.setHotel(hotel);
            return rating;
        }).collect(Collectors.toList());

        user.setRatings(ratingList);
        return user;
    }
}
