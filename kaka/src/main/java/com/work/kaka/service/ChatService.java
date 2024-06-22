package com.work.kaka.service;

import com.work.kaka.dto.UserDTO;
import com.work.kaka.model.Chat;
import com.work.kaka.model.Message;

import java.util.List;

public interface ChatService {
    void createChat(UserDTO participant1, UserDTO participant2);
    boolean sendMessage(Message message);
    List<Message> getChatMessages(Chat chat);
    Chat getChatById(Long chatId);
}
