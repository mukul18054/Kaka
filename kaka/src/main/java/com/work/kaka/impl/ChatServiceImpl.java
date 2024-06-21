package com.work.kaka.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.work.kaka.model.Chat;
import com.work.kaka.model.Message;
import com.work.kaka.model.User;
import com.work.kaka.repository.ChatRepository;
import com.work.kaka.repository.MessageRepository;
import com.work.kaka.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.kafka.core.KafkaTemplate;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate; // Assuming KafkaTemplate is configured

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
        try {
            messageRepository.save(message);
            String messageJson = new ObjectMapper().writeValueAsString(message); // Convert to JSON
            kafkaTemplate.send("chat-messages", messageJson); // Send to Kafka topic
            return true;
        } catch (Exception e) {
            // Handle exceptions (e.g., database errors, Kafka issues)
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Message> getChatMessages(Chat chat) {
        return messageRepository.findByChat(chat);
    }

    @Override
    public Chat getChatById(Long chatId) {
        return chatRepository.findById(chatId).orElse(null);
    }
}
