package com.mindlink.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mindlink.Util.PriorityQueue.PriorityQueue;
import com.mindlink.Util.SimplyLinkedList.SimplyLinkedList;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Data
@Document(collection="students")
public class Student {
    @Id
    String id;
    String name;
    String email;
    String password;
    List<String> interests;

    List<String> studyGroupsIdList; //Se necesita por ser relación m:m, por ahora no se va a utilizar

    @Transient
    SimplyLinkedList<StudyGroup> studyGroupsOwnList;

    @Transient
    SimplyLinkedList<Content> publishedContentsOwnList; //Con el id del estudiante en la clase Content traigo todos los contenidos

    @Transient
    SimplyLinkedList<Valoration> valorationsOwnList;

    @Transient
    PriorityQueue<HelpRequest> helpRequestsOwnList;

    // NUEVO: campos para coincidencias
    @Transient
    List<String> commonGroups;
    @Transient
    List<String> commonInterests;

    public Student(String id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.interests = new LinkedList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student s)) return false;
        return Objects.equals(id, s.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
