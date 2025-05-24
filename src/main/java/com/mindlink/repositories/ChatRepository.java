package com.mindlink.repositories;

import com.mindlink.models.Chat;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface ChatRepository extends MongoRepository<Chat, String> {
    List<Chat> findByParticipantEmailsContaining(String email);

    
}