package com.mindlink.services;

import com.mindlink.models.Message;
import com.mindlink.repositories.MessageRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageService {
    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Message sendMessage(String chatId, String senderId, String text) {
        Message message = new Message();
        message.setChatId(chatId);
        message.setSenderId(senderId);
        message.setText(text);
        message.setTimestamp(LocalDateTime.now());
        return messageRepository.save(message);
    }

    public List<Message> getMessagesByChat(String chatId) {
        return messageRepository.findByChatIdOrderByTimestampAsc(chatId);
    }

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }
}