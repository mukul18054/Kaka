package com.work.kaka.model;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Entity
@Getter
@Setter
public class User {

    @Id
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
    private String backgroundVerificationStatus; // verified, inProcess, rejected, unverified

    // Assuming 'Post' is another entity in your system
    @OneToMany(mappedBy = "author") // Specifies the reverse side of the relation
    private List<Post> posts;

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
}
