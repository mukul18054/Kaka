package com.work.kaka.controller;

import com.work.kaka.dto.UserDTO;
import com.work.kaka.model.*;
import com.work.kaka.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

public class UserController {

    @Autowired
    private UserService userService;
    @PostMapping("/users")
    public ResponseEntity<User> registerUser(@RequestBody UserDTO userDTO,
                                             @RequestParam("password") String password) {

        User newUser = userService.createUser(userDTO, password);
        return ResponseEntity.created(URI.create("/users/" + newUser.getUserId())).body(newUser);
    }



    @GetMapping("/users/{userId}")
    public ResponseEntity<User> getUserProfile(@PathVariable long userId) {
        User user = userService.findById(userId);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @PutMapping("/users/{userId}")
    public ResponseEntity<User> updateUserProfile(@PathVariable long userId, @RequestBody UserDTO userDTO) {
        // ... Authentication: Ensure the user is updating their own profile ...

        User updatedUser = userService.updateUser(userId, userDTO);
        // Consider returning only a success status here (204 No Content)
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<?> deleteUserProfile(@PathVariable long userId) {
        // ... Authentication/Authorization ...
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}
