package com.mindlink.models;

import lombok.Data;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mindlink.Util.SimplyLinkedList.SimplyLinkedList;

@Data
@Document(collection = "studyGroups")
public class StudyGroup {
    @Id
    private String id;
    private String topic;

    private List<String> studentIdList;

    @Transient
    private SimplyLinkedList<Student> studentOwnList;

    public StudyGroup(String id, String topic) {
        this.id = id;
        this.topic = topic;
    }
}
