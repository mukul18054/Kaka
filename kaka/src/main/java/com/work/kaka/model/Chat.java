package com.work.kaka.model;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long chatId;

    @OneToOne
    private Requirement requirement; // (If applicable)

    @ManyToOne
    private User participant1;

    @ManyToOne
    private User participant2;

    @Column(nullable = false)
    private LocalDateTime createdTimestamp;

    @OneToMany(mappedBy = "chat")
    private List<Message> messages;

    // 1. No-Argument Constructor (Often required by frameworks)
    public Chat() {
    }

    // 2. Constructor for Essential Details
    public Chat(Requirement requirement, User participant1, User participant2, LocalDateTime createdTimestamp) {
        this.requirement = requirement;
        this.participant1 = participant1;
        this.participant2 = participant2;
        this.createdTimestamp = createdTimestamp;
    }
}
