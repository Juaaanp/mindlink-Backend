package com.mindlink.services;

import com.mindlink.models.Chat;
import com.mindlink.repositories.ChatRepository;
import com.mindlink.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {
    private final ChatRepository chatRepository;
    private final MessageRepository messageRepository;

    @Autowired
    public ChatService(ChatRepository chatRepository, MessageRepository messageRepository) {
        this.chatRepository = chatRepository;
        this.messageRepository = messageRepository;
    }

    public Chat createChat(List<String> participantEmails) {
        Chat chat = new Chat();
        chat.setParticipantEmails(participantEmails);
        return chatRepository.save(chat);
    }

    public List<Chat> getChatsByParticipant(String email) {
        return chatRepository.findByParticipantEmailsContaining(email);
    }

    public void deleteChat(String id) {
      
        messageRepository.deleteByChatId(id);
     
        chatRepository.deleteById(id);
    }
}