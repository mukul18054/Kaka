package com.work.kaka.model;

// Import necessary annotations (Entity, Id, etc.)

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Entity
public class User {
    @Id
    private long userId;
    private String name;
    private String address;
    private String email;
    private String phone;
    private String faceId;
    private String password;
    private String age;
    private String gender;
//    private List<Task> preferredTaskTypes;

    private List<String> pastTasks;
    private double rating;
    private List<Post> posts;

}
