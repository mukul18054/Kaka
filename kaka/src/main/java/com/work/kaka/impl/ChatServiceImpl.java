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
import org.springframework.dao.DataAccessException;
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
            // Fetch Chat based on sender or recipient (assuming chatId is not available in message)
            User sender = message.getSender();
            User recipient = message.getRecipient();
            Chat chat = chatRepository.findByParticipant1AndParticipant2(sender, recipient);
            if (chat == null) {
                // Handle case where chat doesn't exist between sender and recipient
                return false;
            }
            message.setChat(chat); // Set the chat on the message object
            messageRepository.save(message);
            String messageJson = new ObjectMapper().writeValueAsString(message);
            kafkaTemplate.send("chat-messages", messageJson);
            return true;
        } catch (Exception e) {
            // Handle specific exceptions
            if (e instanceof DataAccessException) {
                // Retry logic for database errors
                // ...
            } else {
                e.printStackTrace();
            }
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
