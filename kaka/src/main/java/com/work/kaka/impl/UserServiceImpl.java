package com.work.kaka.impl;

import com.work.kaka.dto.UserDTO;
import com.work.kaka.model.User;
import com.work.kaka.repository.UserRepository;
import com.work.kaka.service.OtpService;
import com.work.kaka.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private OtpService otpService;

    @Override
    public User createUser(UserDTO userDTO, String password) {
        // Thorough input validation ...
        User user = new User();
        // check if user already exists
        if (userRepository.findByEmail(userDTO.getEmail()) != null) {
            log.error("User already exists with email: {}", userDTO.getEmail());
            return null;
        }
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(userDTO, user); // Using ModelMapper for mapping
        user.setPassword(passwordEncoder.encode(password)); // Encode and save
        user.setBackgroundVerificationStatus("PENDING");
        user.setContactNumber("9876543210");
        log.info("User Created: {}", user);
        return userRepository.save(user);
    }

    @Override
    public User findById(long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        return optionalUser.orElse(null); // Or throw an exception if not found
    }

    @Override
    public User updateUser(long userId, UserDTO updatedUserDTO) {
        User existingUser = findById(userId);
        if (existingUser == null) {
            return null; // Or throw an exception
        }

        // Update fields (Consider which fields are updatable)
        // ... more fields if applicable ...

        return userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(long userId) {
        User existingUser = findById(userId);
        if (existingUser != null) {
            userRepository.delete(existingUser);
        } // Or handle cases where the user was not found
    }

    @Override
    public List<User> getUserList(String name, String role) {
        // Implement filtering based on name and role
        return userRepository.findAll(); // Example: Return all users
    }
}
