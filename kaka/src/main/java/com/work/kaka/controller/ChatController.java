package com.work.kaka.controller;

import com.work.kaka.dto.CreateChatRequest;
import com.work.kaka.dto.SendMessageRequest;
import com.work.kaka.model.Chat;
import com.work.kaka.model.Message;
import com.work.kaka.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;
import com.work.kaka.constants.KafkaConstants;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @Autowired
    private KafkaTemplate<String, Message> kafkaTemplate;

    @PostMapping("/create")
    public void createChat(@RequestBody CreateChatRequest request) {
        chatService.createChat(request.getParticipant1(), request.getParticipant2());
    }

    @PostMapping(value = "/send", consumes = "application/json", produces = "application/json")
    public void sendMessage(@RequestBody Message message) {
        message.setTimestamp(LocalDateTime.now());
        try {
            //Sending the message to kafka topic queue
            kafkaTemplate.send(KafkaConstants.KAFKA_TOPIC, message).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{chatId}/messages")
    public List<Message> getChatMessages(@PathVariable Long chatId) {
        Chat chat = chatService.getChatById(chatId);
        return chatService.getChatMessages(chat);
    }

    @MessageMapping("/sendMessage")
    @SendTo("/topic/group")
    public Message broadcastGroupMessage(@Payload Message message) {
        //Sending this message to all the subscribers
        return message;
    }
}
