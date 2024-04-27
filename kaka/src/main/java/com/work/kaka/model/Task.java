package com.work.kaka.model; // Assuming your model classes are within this package

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity // Signifies this class represents a database table
@Getter // Lombok: Generate getters for all fields
@Setter // Lombok: Generate setters for all fields
public class Task {

    @Id // Marks the primary key
    @GeneratedValue(strategy = GenerationType.AUTO) // Optional, for auto-incrementing IDs
    private long taskId;

    @Column(nullable = false) // Ensures these fields cannot be null
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private double offeredPrice;

    @Column(nullable = false)
    private String status;

    @OneToOne // One-to-one mapping with a User
    @JoinColumn(name = "creator_id", referencedColumnName = "userId") // Customize join details if needed
    private User creator;

    @OneToOne(optional = true) // One-to-one optional mapping for an assignee
    @JoinColumn(name = "assignee_id", referencedColumnName = "userId")
    private User assignee;

    private String location;

    // Constructors (No-args and All-args)
    // ...

    // Getters and Setters
    // ...
}
