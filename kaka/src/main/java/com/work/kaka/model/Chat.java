package com.work.kaka.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long chatId;

//    @OneToOne(mappedBy = "requirement") // Assuming a requirement can only have one chat
//    private Requirement requirement;

    // Relationship to Users: Debatable. If a chat is ALWAYS 1-to-1 between users, you could model it with:
    @ManyToOne
    @JoinColumn(name = "participant1_id", nullable = false)
    private User participant1;

    @ManyToOne
    @JoinColumn(name = "participant2_id", nullable = false)
    private User participant2;

    @Column(nullable = false)
    private LocalDateTime createdTimestamp;

    @OneToMany(mappedBy = "chat")
    private List<Message> messages;

    // 2. Constructor for Essential Details
    public Chat(Requirement requirement, User participant1, User participant2, LocalDateTime createdTimestamp) {
//        this.requirement = requirement;
        this.participant1 = participant1;
        this.participant2 = participant2;
        this.createdTimestamp = createdTimestamp;
    }
}
