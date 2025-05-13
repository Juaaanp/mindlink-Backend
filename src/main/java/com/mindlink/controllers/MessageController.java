package com.mindlink.controllers;

import com.mindlink.models.Message;
import com.mindlink.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping
    public ResponseEntity<Message> sendMessage(@RequestBody Message message) {
        return ResponseEntity.ok(messageService.save(message));
    }

    @GetMapping("/byChat/{chatId}")
    public ResponseEntity<List<Message>> getMessagesByChat(@PathVariable String chatId) {
        return ResponseEntity.ok(messageService.findByChatId(chatId));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Message>> getAllMessages() {
        return ResponseEntity.ok(messageService.findAll());
    }


}