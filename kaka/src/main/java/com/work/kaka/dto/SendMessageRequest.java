package com.work.kaka.dto;

import com.work.kaka.model.Chat;
import com.work.kaka.model.Message;
import com.work.kaka.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendMessageRequest {
    private Message message;
}