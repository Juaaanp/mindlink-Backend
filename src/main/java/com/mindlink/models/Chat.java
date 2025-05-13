package com.mindlink.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "chats")
public class Chat {
    @Id
    private String id;
    private List<String> participantIds; // IDs de los usuarios participantes
}