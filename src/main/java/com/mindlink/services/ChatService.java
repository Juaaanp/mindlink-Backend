package com.mindlink.services;

import com.mindlink.models.Chat;
import com.mindlink.repositories.ChatRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ChatService {
    private final ChatRepository chatRepository;

    public ChatService(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }
public Chat createChat(List<String> participantEmails) {
    Chat chat = new Chat();
    chat.setParticipantEmails(participantEmails);
    return chatRepository.save(chat);
}

public List<Chat> getChatsByParticipant(String email) {
    return chatRepository.findByParticipantEmailsContaining(email);
}
}