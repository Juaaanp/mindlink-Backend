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
        return chatService.createChat(request.getParticipantIds());
    }

    @GetMapping("/participant/{participantId}")
    public List<Chat> getChatsByParticipant(@PathVariable String participantId) {
        return chatService.getChatsByParticipant(participantId);
    }
}