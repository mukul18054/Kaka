package com.work.kaka.dto;

import com.work.kaka.model.Message;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendMessageDTO {
    private Message message;
}