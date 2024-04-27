package com.work.kaka.model;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;  // For dates
import java.time.LocalDateTime; // For timestamps
import java.util.List;

@Entity
@Getter
@Setter
public class Requirement {

    // Consider using an Enum for status
    public enum RequirementStatus {
        OPEN, IN_PROGRESS, COMPLETED, CANCELLED
    }


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long requirementId;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private double budget;

    @Column(nullable = false)
    private LocalDate serviceNeededDate;

    @Column(nullable = false)
    private LocalDateTime postedTimestamp;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private RequirementStatus status; // Consider an Enum (see below)

    @OneToOne
    private User postedBy; // User who created the requirement

    @OneToMany(mappedBy = "offeredFor")
    private List<Offer> offersReceived;

    @OneToOne(mappedBy = "requirement") // Inverse side if Task-Requirement is bi-directional
    private Task task;

    @OneToOne(mappedBy = "requirement")
    private Chat chat;


    // 1. No-Argument Constructor (Often required by frameworks)
    public Requirement() {
    }

    // 2. Constructor for Essential Details
    public Requirement(String description, double budget, LocalDate serviceNeededDate, String location, User postedBy) {
        this.description = description;
        this.budget = budget;
        this.serviceNeededDate = serviceNeededDate;
        this.location = location;
        this.postedBy = postedBy;
        this.postedTimestamp = LocalDateTime.now(); // Auto-set posting time
        this.status = RequirementStatus.OPEN; // Auto-set default status
    }

    // 3. (Optional) Constructor for All Fields (Less common)
    public Requirement(long requirementId, String description, double budget, LocalDate serviceNeededDate, LocalDateTime postedTimestamp, String location, String status, User postedBy, List<Offer> offersReceived, Task task) {
        this.requirementId = requirementId;
        // ... set other fields similarly
    }
}
