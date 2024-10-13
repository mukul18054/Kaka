package com.work.kaka.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MessageDTO {
    private String content;
    private Long senderId;
    private Long recipientId;

    // Getters and setters
}
