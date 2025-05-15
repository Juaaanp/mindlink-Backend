package com.mindlink.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@Document(collection = "chats")
public class Chat {
    @Id
    private String id;
    private List<String> participantIds;
}
