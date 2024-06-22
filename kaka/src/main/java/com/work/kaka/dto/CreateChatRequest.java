package com.work.kaka.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateChatRequest {
    private UserDTO participant1;
    private UserDTO participant2;
}
