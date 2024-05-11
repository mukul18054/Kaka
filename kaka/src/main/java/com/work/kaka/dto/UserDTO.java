package com.work.kaka.dto;

import jakarta.persistence.Column;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    // auto generate id

    //    private Long id; // Include ID if needed for updates
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private String phone;
    private String email;
    private String idType;
    private String backgroundVerificationStatus;
    private int age;
    private String gender;
    private double rating;

    public UserDTO(String name, String address, String email, String phone) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
    }
}