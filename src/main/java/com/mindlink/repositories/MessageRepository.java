package com.mindlink.repositories;

import com.mindlink.models.Message;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface MessageRepository extends MongoRepository<Message, String> {
    List<Message> findByChatIdOrderByTimestampAsc(String chatId);

    
    void deleteByChatId(String chatId);
}