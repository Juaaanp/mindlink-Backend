package com.mindlink.models;

import com.mindlink.Util.DoublyLinkedList.DoublyLinkedList;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

@Data
@Document(collection="students")
public class Student {
    @Id
    String id;
    String name;
    String email;
    String password;
    List<String> interests;
    DoublyLinkedList<Content> publishedContents;
    DoublyLinkedList<Valoration> valorations;
    PriorityQueue<HelpRequest> helpRequests; // check in the future.

    public Student(String id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.interests = new LinkedList<>();
        this.publishedContents = new DoublyLinkedList<>();
        this.valorations = new DoublyLinkedList<>();
        this.helpRequests = new PriorityQueue<>();
    }
}
