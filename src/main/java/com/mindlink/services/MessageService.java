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

    public void deleteMessage(String messageId) {
        messageRepository.deleteById(messageId); // Necesario oara eliminar el mensaje por su ID
    }

    public Message editMessage(String messageId, String newText) {
        Message message = messageRepository.findById(messageId)
                .orElseThrow(() -> new RuntimeException("Mensaje no encontrado"));
        message.setText(newText);

        return messageRepository.save(message);
    }

}