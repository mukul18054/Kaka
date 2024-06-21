package com.work.kaka.dto;

import com.work.kaka.model.User;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateChatRequest {
    private User participant1;
    private User participant2;
}
