package com.work.kaka.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.work.kaka.dto.UserDTO;
import com.work.kaka.model.*;
import com.work.kaka.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    @PostMapping("/create")
    public ResponseEntity<User> registerUser(@RequestBody UserDTO userDTO) {
        User newUser = userService.createUser(userDTO, "password");
        return ResponseEntity.created(URI.create("/users/" + newUser.getUserId())).body(newUser);
    }



    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserProfile(@PathVariable long userId) {
        User user = userService.findById(userId);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUserProfile(@PathVariable long userId, @RequestBody UserDTO userDTO) {
        // ... Authentication: Ensure the user is updating their own profile ...

        User updatedUser = userService.updateUser(userId, userDTO);
        // Consider returning only a success status here (204 No Content)
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUserProfile(@PathVariable long userId) {
        // ... Authentication/Authorization ...
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/listUsers") // Example: "/users/listUsers?name=John&role=ADMIN
//    @PreAuthorize("hasRole('ROLE_ADMIN')") // Assuming Spring Security for role-based authorization
    public ResponseEntity<List<User>> listUsers(@RequestParam(required = false) String name,
                                                @RequestParam(required = false) String role) {
        List<User> users = userService.getUserList(name, role); // Implement filtering in your service
        return ResponseEntity.ok(users);
    }


//    @PostMapping("/reset-password-request")
//    public ResponseEntity<?> requestPasswordReset(@RequestBody ResetPasswordRequest request) {
//        userService.generatePasswordResetToken(request.getEmail());
//        // Inside userService, send an email with the reset link containing the token
//        return ResponseEntity.ok("Password reset email sent.");
//    }
//
//    @PutMapping("/reset-password")
//    public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordDto resetPasswordDto) {
//        userService.resetPassword(resetPasswordDto.getToken(), resetPasswordDto.getNewPassword());
//        return ResponseEntity.ok("Password updated successfully.");
//    }
}
