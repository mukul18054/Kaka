package com.work.kaka.model;

import com.work.kaka.model.User;
import lombok.Getter;
import lombok.Setter;

//import javax.persistence.*;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long messageId;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    @OneToOne(optional = false)
    private User sender;

    @OneToOne(optional = false)
    private User recipient;

    @ManyToOne
    @JoinColumn(nullable = false) // Messages always belong to a chat
    private Chat chat;


    // Potentially add a 'read' flag (boolean) if you'd like to track that

    // 1. No-Argument Constructor (Often required by frameworks)
    public Message() {
    }

    // 2. Constructor for Essential Details
    public Message(String content, LocalDateTime timestamp, User sender, User recipient) {
        this.content = content;
        this.timestamp = timestamp;
        this.sender = sender;
        this.recipient = recipient;
    }
}
