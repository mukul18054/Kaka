package com.work.kaka.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//import javax.persistence.*;
import jakarta.persistence.*;
import java.time.LocalDateTime; // For offer timestamp

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long offerId;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private double offeredPrice;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    @OneToOne
    @JoinColumn(name = "offered_by", referencedColumnName = "userId")
    private User offeredBy;

    @OneToOne
    @JoinColumn(name = "offered_for", referencedColumnName = "requirementId")
    private Requirement offeredFor;


    // 2. Constructor for Essential Details
    public Offer(String description, double offeredPrice, LocalDateTime timestamp, User offeredBy, Requirement offeredFor) {
        this.description = description;
        this.offeredPrice = offeredPrice;
        this.timestamp = timestamp;
        this.offeredBy = offeredBy;
        this.offeredFor = offeredFor;
    }

    // 3. Additional Constructors as needed
    public Offer(String description, double offeredPrice, User offeredBy, Requirement offeredFor) {
        this.description = description;
    }
}
