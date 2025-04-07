package com.mindlink.models;

import com.mindlink.Util.DoublyLinkedList.DoublyLinkedList;
import lombok.Data;

@Data
public class StudyGroup {
    private Integer id;
    private String topic;
    private DoublyLinkedList<Student> studentsList;

    public StudyGroup(Integer id, String topic) {
        this.id = id;
        this.topic = topic;
        this.studentsList = new DoublyLinkedList<>();
    }
}
