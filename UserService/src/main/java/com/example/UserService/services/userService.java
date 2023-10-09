package com.example.UserService.services;

import com.example.UserService.entities.User;

import java.util.List;

public interface userService {
    //create
    User createUser(User user);

    //get all
    List<User> getAllUsers();

    //get by id
    User getUserById(String Id);
}
