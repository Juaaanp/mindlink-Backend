package com.mindlink.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mindlink.Util.DoublyLinkedList.DoublyLinkedList;
import com.mindlink.Util.PriorityQueue.PriorityQueue;

import java.util.LinkedList;
import java.util.List;

@Data
@Document(collection="students")
public class Student {
    @Id
    String id;
    String name;
    String email;
    String password;
    List<String> interests;

    List<String> studyGroupsIdList; //Se necesita por ser relación m:m

    @Transient
    DoublyLinkedList<StudyGroup> studyGroupsOwnList;

    @Transient
    DoublyLinkedList<Content> publishedContentsOwnList; //Con el id del estudiante en la clase Content traigo todos los contenidos

    @Transient
    DoublyLinkedList<Valoration> valorationsOwnList;

    @Transient
    PriorityQueue<HelpRequest> helpRequestsOwnList;

    public Student(String id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.interests = new LinkedList<>();
    }
}
