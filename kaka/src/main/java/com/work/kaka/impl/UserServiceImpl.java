package com.work.kaka.impl;

import com.work.kaka.dto.UserDTO;
import com.work.kaka.model.User;
import com.work.kaka.repository.UserRepository;
import com.work.kaka.service.UserService;
import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    @Override
    public User createUser(UserDTO userDTO, String password) {
        // Thorough input validation ...
        User user = new User();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(userDTO, user); // Using ModelMapper for mapping
//        user.setPassword(passwordEncoder.encode(password)); // Encode and save
        user.setPassword(password); // Encode and save
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
}
