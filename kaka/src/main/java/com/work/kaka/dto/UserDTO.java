package com.work.kaka.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private Long id; // Include ID if needed for updates
    private String name;
    private String address;
    private String email;

    // Getters and Setters, Constructors
}