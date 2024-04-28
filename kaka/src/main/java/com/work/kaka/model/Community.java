package com.work.kaka.model;

import lombok.Getter;
import lombok.Setter;

//import javax.persistence.*;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
public class Community {

    // Consider using an Enum for status
    public enum CommunityStatus {
        ACTIVE, INACTIVE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long communityId;

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

    @Column(nullable = false)
    private CommunityStatus communityStatus;  // Consider an Enum for values like 'Active', 'Pending', etc.

    @Column
    private String communityJoiningFees;

    @Column(nullable = false)
    private LocalDate communityDateOfCreation;

    @OneToOne
    @JoinColumn(name = "admin_id", referencedColumnName = "userId")
    private User admin;

    @ManyToMany
    @JoinTable( // Customize join table if needed
            name = "user_community",
            joinColumns = @JoinColumn(name = "community_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "community", cascade = CascadeType.ALL)
//    private List<Requirement> communityPosts;

    @ElementCollection
    private List<User> communityOwners; // User IDs

    @Column
    private String communityManager;

    @ManyToMany(mappedBy = "communities") // Inverse side
    private List<User> communitymembers;

    @Column
    private double communityRating; // Use 'double' for more detailed ratings

    @ElementCollection
    private List<String> communitySuggestion;

    @ElementCollection
    private List<String> communityComplaint;

    // 1. No-Argument Constructor (Often required by JPA)
    public Community() {
    }

    // 2. Constructor for Essential Details
    public Community(String communityName, String communityDescription, String communityLocation, User admin) {
        this.communityName = communityName;
        this.communityDescription = communityDescription;
        this.communityLocation = communityLocation;
        this.admin = admin;
        this.communityDateOfCreation = LocalDate.now(); // Auto-set creation date
        this.communityStatus = CommunityStatus.ACTIVE; // Set a default active status
    }

    // 3. (Optional) Constructor for All Fields (Less Common)
    public Community(long communityId, String communityName, String communityDescription) {
        this.communityId = communityId;
        // ... set other fields similarly
    }
}
