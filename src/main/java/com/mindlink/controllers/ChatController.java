package com.mindlink.controllers;

import com.mindlink.models.Chat;
import com.mindlink.services.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/chats")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @PostMapping
    public ResponseEntity<Chat> createChat(@RequestBody Chat chat) {
        Optional<Chat> existingChat = chatService.findChatByParticipants(chat.getParticipantIds());
        if (existingChat.isPresent()) {
            return ResponseEntity.ok(existingChat.get());
        }
        return ResponseEntity.ok(chatService.save(chat));
    }

    @GetMapping
    public ResponseEntity<List<Chat>> getAllChats() {
        return ResponseEntity.ok(chatService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Chat> getChatById(@PathVariable String id) {
        Optional<Chat> chat = chatService.findById(id);
        return chat.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/participant/{participantId}")
    public ResponseEntity<List<Chat>> getChatsByParticipant(@PathVariable String participantId) {
        return ResponseEntity.ok(chatService.findByParticipant(participantId));
    }
}