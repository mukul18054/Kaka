package com.work.kaka.model;

import lombok.Getter;
import lombok.Setter;

//import javax.persistence.*;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String contactNumber;

    @Column(nullable = false)
    private String email;

    @Column(nullable = true)
    private String idType; // mobile by default

    @Column(nullable = true)
    private String idNumber; // mobile number by default

    @Column(nullable = false)
    private String backgroundVerificationStatus = "PENDING"; // verified, inProcess, rejected, unverified

    // Assuming 'Post' is another entity in your system
    @OneToMany(mappedBy = "postedBy") // Specifies the reverse side of the relation
    private List<Requirement> requirement;

    @OneToMany(mappedBy = "assignee")
    private List<Task> assignedTasks;

    @Column(nullable = false)
    private String password; // Sensitive data, should always be stored securely

    @Lob // Large Object (suitable for images, documents, etc.)
    @Column(nullable = true) // Image could be optional
    private byte[] profileImage;

    private int age;

    private String gender;

    @ElementCollection // Appropriate for storing a simple list of strings
    private List<String> pastTasksDone;

    private double rating;

    @ManyToMany
    @JoinTable( // Customize join table details if necessary
            name = "user_community",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "community_id")
    )
    private List<Community> communities;

    // 1. No-Argument Constructor (Often required by frameworks)
    public User() {
    }

    // 2. Constructor for Essential Details
    public User(String name, String address, String contactNumber, String email, String password) {
        this.name = name;
        this.address = address;
        this.contactNumber = contactNumber;
        this.email = email;
        this.password = password;
        this.backgroundVerificationStatus = "unverified"; // Default status
        this.rating = 0.0; // Default rating
    }

    // 3. Additional Constructor for Full Details
    public User(long userId, String name, String address, String contactNumber, String email, String idType,
                String idNumber, String backgroundVerificationStatus, List<Requirement> requirement, String password,
                byte[] profileImage , int age) {
        this.userId = userId;
        this.name = name;
        this.address = address;
        this.contactNumber = contactNumber;
        this.email = email;
        this.idType = idType;
        this.idNumber = idNumber;
        this.backgroundVerificationStatus = backgroundVerificationStatus;
        this.requirement = requirement;
        this.password = password;
        this.profileImage = profileImage;
        this.age = age;
    }
}
