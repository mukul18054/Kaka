//package com.work.kaka.model;
//
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.Setter;
//import java.time.LocalDateTime;
//
////@Entity
//@Getter
//@Setter
//public class Notification {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private long notificationId;
//    @Column(nullable = false)
//    private String content;
//    @Column(nullable = false)
//    private LocalDateTime timestamp;
//    @ManyToOne
//    @JoinColumn(name = "user_id", referencedColumnName = "userId", nullable = false)
//    private User user;
//    private boolean read;
//}
