package com.mindlink.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Document(collection = "valorations")
public class Valoration {
    @Id
    private String id;
    private Student student;
    private Content content;
    private Byte rate;
    private String comment;
}
