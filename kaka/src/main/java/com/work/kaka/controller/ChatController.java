package com.work.kaka.controller;

import com.work.kaka.dto.CreateChatDTO;
import com.work.kaka.dto.SendMessageDTO;
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
    public void createChat(@RequestBody CreateChatDTO request) {
        chatService.createChat(request.getParticipant1(), request.getParticipant2());
    }

    @PostMapping("/send")
    public boolean sendMessage(@RequestBody SendMessageDTO request) {
        return chatService.sendMessage(request.getMessage());
    }

    @GetMapping("/{chatId}/messages")
    public List<Message> getChatMessages(@PathVariable Long chatId) {
        Chat chat = chatService.getChatById(chatId);
        return chatService.getChatMessages(chat);
    }
}
