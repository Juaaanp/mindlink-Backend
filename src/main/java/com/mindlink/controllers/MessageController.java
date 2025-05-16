package com.mindlink.controllers;

import com.mindlink.models.Message;
import com.mindlink.services.MessageService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/messages")
public class MessageController {
    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping
    public Message sendMessage(@RequestBody Map<String, Object> payload) {
        String chatId = payload.get("chatId").toString();
        String senderId = payload.get("senderId").toString();
        String text = payload.get("text").toString();
        return messageService.sendMessage(chatId, senderId, text);
    }

    @GetMapping("/byChat/{chatId}")
    public List<Message> getMessagesByChat(@PathVariable String chatId) {
        return messageService.getMessagesByChat(chatId);
    }

    @GetMapping
    public List<Message> getAllMessages() {
        return messageService.getAllMessages();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMessage(@PathVariable String id) {
        messageService.deleteMessage(id);
        return ResponseEntity.noContent().build();
    }

}