package com.example.UserService.services.impl;


import com.example.UserService.Exceptions.ResourceNotFound;
import com.example.UserService.entities.Hotel;
import com.example.UserService.entities.Rating;
import com.example.UserService.entities.User;
import com.example.UserService.external.Services.HotelService;
import com.example.UserService.respositry.userRespositry;
import com.example.UserService.services.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class userImpl implements userService {

    @Autowired
    public userRespositry userDao;

    @Autowired
    public RestTemplate restClient;

    @Autowired
    private HotelService hotelService;

    @Override
    public User createUser(User user) {
        String randomId = UUID.randomUUID().toString();
        user.setUserId(randomId);
        return userDao.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    @Override
    public User getUserById(String Id) {
        User u =  userDao.findById(Id).orElseThrow(() -> new ResourceNotFound("Unable to find user with id : " + Id));

        Rating[] ratings = restClient.getForObject("http://RATING-SERVICE/ratings/users/" + Id, Rating[].class);
        List<Rating> ratingOfUsers = Arrays.asList(ratings);
//
         List<Rating> ratingList = ratingOfUsers.stream().map(rating -> {
           Hotel h = hotelService.getHotelById(rating.getHotelId());
           rating.setHotel(h);
           return rating;
        }).collect(Collectors.toList());
        u.setRatings(ratingOfUsers);
        return u;
    }
}
