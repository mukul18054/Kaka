package com.work.kaka.service;

import com.work.kaka.dto.UserDTO;
import com.work.kaka.model.User;
import org.springframework.stereotype.Service;

import java.util.List; // Add if necessary


public interface UserService {

    User createUser(UserDTO userDTO, String password);

    User findById(long userId);

    User updateUser(long userId, UserDTO updatedUserDTO);

    void deleteUser(long userId);

    List<User> getUserList(String name, String role);


    // Additional methods if needed:

    // List<User> findAll(); // If you want a method to get all users
    // List<User> findByLocation(String location); // Example of a potential custom query

}
