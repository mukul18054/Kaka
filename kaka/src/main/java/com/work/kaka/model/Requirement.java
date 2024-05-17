package com.work.kaka.model;

//import javax.persistence.*;
import jakarta.persistence.*;

import lombok.*;

import java.time.LocalDate;  // For dates
import java.time.LocalDateTime; // For timestamps
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

    @Column
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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "chat_id")
    private Chat chat;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "community_id")
    private Community community;


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
}
