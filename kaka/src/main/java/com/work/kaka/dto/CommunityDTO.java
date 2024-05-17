package com.work.kaka.dto;


import com.work.kaka.model.Community;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommunityDTO {
    private Long communityId;
    private String communityName;
    private String communityDescription;
    private String communityLocation;
    private String communityType;
    private String communitySize;
    private Community.CommunityStatus communityStatus;
    private String communityJoiningFees;
    private LocalDate communityDateOfCreation;
    private UserDTO admin; // DTO for the admin user
    private List<UserDTO> communityMembers; // DTOs for members
    private double communityRating;
    private List<String> communitySuggestions;
    private List<String> communityComplaints;
    // Add getters and setters for all fields
}


