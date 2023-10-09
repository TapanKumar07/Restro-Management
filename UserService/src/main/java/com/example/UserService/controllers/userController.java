package com.example.UserService.controllers;

import com.example.UserService.entities.User;
import com.example.UserService.services.userService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class userController {
    @Autowired
    private userService UserService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User u) {
        User Response = UserService.createUser(u);
        return new ResponseEntity<User>(Response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        List<User> Response = UserService.getAllUsers();
        return new ResponseEntity<List<User>>(Response, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    @Retry(name = "ratingHotelRetry", fallbackMethod = "ratingHotelFallbackRetry")
    @CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
    @RateLimiter(name = "userRateLimiter", fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<User> getUserById(@PathVariable String userId) {
        User Response = UserService.getUserById(userId);
        return new ResponseEntity<User>(Response, HttpStatus.OK);
    }

    public ResponseEntity<User> ratingHotelFallback(String userId, Exception ex) {
        User u = User.builder().userId("123").about("Dummy User").email("Dummy@gmail.com").name("Server is down right now").build();
        return new ResponseEntity<User>(u, HttpStatus.OK);
    }
    public ResponseEntity<User> ratingHotelFallbackRetry(String userId, Exception ex) {
        User u = User.builder().userId("123").about("Dummy User").email("Dummy@gmail.com").name("Retrying Server Might Be Slow").build();
        return new ResponseEntity<User>(u, HttpStatus.OK);
    }



}
