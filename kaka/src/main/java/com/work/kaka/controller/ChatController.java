package com.work.kaka.controller;

import com.work.kaka.dto.CreateChatRequest;
import com.work.kaka.dto.SendMessageRequest;
import com.work.kaka.model.Chat;
import com.work.kaka.model.Message;
import com.work.kaka.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @PostMapping("/create")
    public Chat createChat(@RequestBody CreateChatRequest request) {
        return chatService.createChat(request.getParticipant1(), request.getParticipant2());
    }

    @PostMapping("/send")
    public Message sendMessage(@RequestBody SendMessageRequest request) {
        return chatService.sendMessage(request.getChat(), request.getSender(), request.getRecipient(), request.getContent());
    }

    @GetMapping("/{chatId}/messages")
    public List<Message> getChatMessages(@PathVariable Long chatId) {
        Chat chat = chatService.getChatById(chatId);
        return chatService.getChatMessages(chat);
    }
}
