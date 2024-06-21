package com.work.kaka.impl;

import com.work.kaka.model.Chat;
import com.work.kaka.model.Message;
import com.work.kaka.model.User;
import com.work.kaka.repository.ChatRepository;
import com.work.kaka.repository.MessageRepository;
import com.work.kaka.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public void createChat(User participant1, User participant2) {
        Chat chat = new Chat();
        chat.setParticipant1(participant1);
        chat.setParticipant2(participant2);
        chat.setCreatedTimestamp(LocalDateTime.now());
        chatRepository.save(chat);
    }

    @Override
    public boolean sendMessage(Message message) {
        Chat chat = message.getChat();
        chat.getMessages().add(message);
        messageRepository.save(message);
        return true;
    }

    @Override
    public List<Message> getChatMessages(Chat chat) {
        return chat.getMessages();
    }

    @Override
    public Chat getChatById(Long chatId) {
        return chatRepository.findById(chatId).orElseThrow(() -> new RuntimeException("Chat not found"));
    }
}
