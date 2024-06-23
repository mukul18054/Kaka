package com.work.kaka.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.work.kaka.dto.UserDTO;
import com.work.kaka.model.Chat;
import com.work.kaka.model.Message;
import com.work.kaka.model.User;
import com.work.kaka.repository.ChatRepository;
import com.work.kaka.repository.MessageRepository;
import com.work.kaka.repository.UserRepository;
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

//    @Autowired
//    private KafkaTemplate<String, String> kafkaTemplate; // Assuming KafkaTemplate is configured

    @Autowired
    private UserRepository userRepository;
    @Override
    public void createChat(UserDTO participant1, UserDTO participant2) {
        Chat chat = new Chat();
        // get User using email from userDTO
        User participant1User = userRepository.findByEmail(participant1.getEmail());
        User participant2User = userRepository.findByEmail(participant2.getEmail());
        chat.setParticipant1(participant1User);
        chat.setParticipant2(participant2User);
        chat.setCreatedTimestamp(LocalDateTime.now());
        chatRepository.save(chat);
    }

    @Override
    public boolean sendMessage(Message message) {
        try {
            messageRepository.save(message);
            String messageJson = new ObjectMapper().writeValueAsString(message); // Convert to JSON
//            kafkaTemplate.send("chat-messages", messageJson); // Send to Kafka topic
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
