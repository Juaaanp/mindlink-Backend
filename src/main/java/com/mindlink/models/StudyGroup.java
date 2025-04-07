package com.mindlink.models;

import com.mindlink.Util.DoublyLinkedList.DoublyLinkedList;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "studyGroups")
public class StudyGroup {
    @Id
    private String id;
    private String topic;
    private DoublyLinkedList<Student> studentsList;

    public StudyGroup(String id, String topic) {
        this.id = id;
        this.topic = topic;
        this.studentsList = new DoublyLinkedList<>();
    }
}
