package com.mindlink.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Document(collection = "messages")
public class Message {
    @Id
    private String id;
    private String chatId;
    private String senderId;
    private String text;
    private LocalDateTime timestamp;
}
