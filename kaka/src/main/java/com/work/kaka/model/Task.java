package com.work.kaka.model;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Task {


    // Consider using an Enum for status
    public enum TaskStatus {
        ACTIVE, IN_PROGRESS, COMPLETED, CANCELLED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long taskId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private double offeredPrice;

    @Column(nullable = false)
    private TaskStatus status;  //  Consider using an Enum here (see below)

    @OneToOne
    @JoinColumn(name = "creator_id", referencedColumnName = "userId")
    private User creator;

    @OneToOne(optional = true)
    @JoinColumn(name = "assignee_id", referencedColumnName = "userId")
    private User assignee;

    private String location;

    @OneToOne
    private Requirement requirement; // Reference to the originating Requirement


    public Task() {
    }

    public Task(String title, String description, double offeredPrice, String status, User creator, User assignee, String location, Requirement requirement) {
        this.title = title;
        this.description = description;
        this.offeredPrice = offeredPrice;
        this.status = TaskStatus.ACTIVE;
        this.creator = creator;
        this.assignee = assignee;
        this.location = location;
        this.requirement = requirement;
    }


}
