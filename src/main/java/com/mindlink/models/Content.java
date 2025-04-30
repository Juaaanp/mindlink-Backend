package com.mindlink.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mindlink.Util.SimplyLinkedList.SimplyLinkedList;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "contents")
public class Content {
    @Id
    private String id;
    private String title;
    private String authorId; //Ahora se referencia con id
    private String type;
    private String topic;

    @Transient
    private String authorName;
    
    @Transient
    private SimplyLinkedList<Valoration> valorationsOwnList;
}
