package com.mindlink.services;

import com.mindlink.models.Chat;
import com.mindlink.repositories.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChatService {
    @Autowired
    private ChatRepository chatRepository;

    public Chat save(Chat chat) {
        return chatRepository.save(chat);
    }

    public List<Chat> findAll() {
        return chatRepository.findAll();
    }

    public Optional<Chat> findById(String id) {
        return chatRepository.findById(id);
    }

    public List<Chat> findByParticipant(String participantId) {
        return chatRepository.findByParticipantIdsContaining(participantId);
    }

    // Nuevo método para evitar duplicados
    public Optional<Chat> findChatByParticipants(List<String> participantIds) {
        if (participantIds == null || participantIds.size() < 2) return Optional.empty();
        List<Chat> chats = chatRepository.findByParticipantIdsContaining(participantIds.get(0));
        for (Chat chat : chats) {
            List<String> chatParticipants = chat.getParticipantIds();
            if (chatParticipants.size() == participantIds.size()) {
                List<String> sortedA = participantIds.stream().sorted().toList();
                List<String> sortedB = chatParticipants.stream().sorted().toList();
                if (sortedA.equals(sortedB)) {
                    return Optional.of(chat);
                }
            }
        }
        return Optional.empty();
    }
}