package com.mindlink.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Document(collection = "moderators")
public class Moderator {
    @Id
    private String id;
    private String name;
    private String email;
    private String password;
}
