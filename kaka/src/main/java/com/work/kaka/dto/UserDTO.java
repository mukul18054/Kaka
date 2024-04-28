package com.work.kaka.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    // auto generate id

    private Long id; // Include ID if needed for updates
    private String name;
    private String address;
    private String email;

    // Getters and Setters, Constructors
    public UserDTO() {
    }

    public UserDTO(String name, String address, String email) {
        this.name = name;
        this.address = address;
        this.email = email;
    }
}