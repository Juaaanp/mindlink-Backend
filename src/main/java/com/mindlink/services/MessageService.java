package com.mindlink.services;

import com.mindlink.models.Message;
import com.mindlink.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    public Message save(Message message) {
        return messageRepository.save(message);
    }

    public List<Message> findByChatId(String chatId) {
        return messageRepository.findByChatIdOrderByTimestampAsc(chatId);
    }

    public List<Message>findAll() {
        return messageRepository.findAll();
    }
}