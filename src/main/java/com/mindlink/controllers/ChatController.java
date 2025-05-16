package com.mindlink.controllers;

import com.mindlink.models.Chat;
import com.mindlink.services.ChatService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chats")
public class ChatController {
    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping
    public Chat createChat(@RequestBody ChatCreateRequest request) {
        return chatService.createChat(request.getParticipantEmails());
    }

    @GetMapping("/participant/{email}")
    public List<Chat> getChatsByParticipant(@PathVariable String email) {
        return chatService.getChatsByParticipant(email);
    }

    @DeleteMapping("/{id}")
    public void deleteChat(@PathVariable String id) {
        chatService.deleteChat(id);
    }
}