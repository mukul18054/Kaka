package com.work.kaka.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder // Use Builder to help create instances of Community
public class Community {

    public enum CommunityStatus {
        ACTIVE, INACTIVE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long communityId;

    @Column(nullable = false)
    private String communityName;

    @Column(nullable = false)
    private String communityDescription;

    @Column(nullable = false)
    private String communityLocation;

    @Column
    private String communityType;

    @Column
    private String communitySize;

    @Enumerated(EnumType.STRING) // Use EnumType.STRING for storing enums as strings
    @Column(nullable = false)
    private CommunityStatus communityStatus;

    @Column
    private String communityJoiningFees;

    @Column(nullable = false)
    private LocalDate communityDateOfCreation;

    @ManyToOne // A Community has one Admin (User)
    @JoinColumn(name = "admin_id", referencedColumnName = "userId")
    private User admin;

    @ManyToMany(mappedBy = "communities") // Bidirectional relationship
    private List<User> communityMembers;

    @OneToMany(mappedBy = "community", cascade = CascadeType.ALL) // Each community has many posts
    private List<Requirement> communityPosts;


    @Column
    private double communityRating;

    @ElementCollection
    private List<String> communitySuggestions; // Using plural for clarity

    @ElementCollection
    private List<String> communityComplaints; // Using plural for clarity

    // Constructor with essential fields
    public Community(String communityName, String communityDescription, String communityLocation, User admin) {
        this.communityName = communityName;
        this.communityDescription = communityDescription;
        this.communityLocation = communityLocation;
        this.communityStatus = CommunityStatus.ACTIVE;
        this.communityDateOfCreation = LocalDate.now();
        this.admin = admin;
    }

    // Builder for easier object creation
    public static class CommunityBuilder {
        // ... builder methods for each field ...
    }

}
