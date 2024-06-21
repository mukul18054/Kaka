package com.work.kaka.service;

import com.work.kaka.model.Chat;
import com.work.kaka.model.Message;
import com.work.kaka.model.User;

import java.util.List;

public interface ChatService {
    void createChat(User participant1, User participant2);
    boolean sendMessage(Message message);
    List<Message> getChatMessages(Chat chat);
    Chat getChatById(Long chatId);
}
