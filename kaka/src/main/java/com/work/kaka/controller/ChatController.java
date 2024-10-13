package com.work.kaka.controller;

import com.work.kaka.dto.CreateChatDTO;
import com.work.kaka.model.Chat;
import com.work.kaka.model.Message;
import com.work.kaka.dto.MessageDTO;
import com.work.kaka.model.User;
import com.work.kaka.repository.MessageRepository;
import com.work.kaka.repository.UserRepository;
import com.work.kaka.service.ChatService;
import com.work.kaka.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;

@Controller
public class ChatController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ChatService chatService;

    @PostMapping("/create")
    public void createChat(@RequestBody CreateChatDTO request) {
        chatService.createChat(request.getParticipant1(), request.getParticipant2());
    }

    @MessageMapping("/chat.sendMessage")
    public void sendMessage(@Payload MessageDTO messageDTO) {
        User sender = userService.findById(messageDTO.getSenderId());
        User recipient = userService.findById(messageDTO.getRecipientId());
        Chat chat = chatService.findByParticipant1AndParticipant2(sender, recipient);

        Message message = Message.builder().chat(chat)
                .content(messageDTO.getContent())
                .sender(sender)
                .recipient(recipient)
                .timestamp(LocalDateTime.now())
                .build();



        messagingTemplate.convertAndSendToUser(
                String.valueOf(recipient.getUserId()),
                "/queue/messages",
                message
        );
    }
}