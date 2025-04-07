package com.mindlink.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Document(collection = "contents")
public class Content {
    @Id
    private String id;
    private String title;
    private Student author;
    private String type;
    private String topic;
    // valoration's list (Optional)


}
