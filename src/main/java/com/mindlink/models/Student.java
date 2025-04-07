package com.mindlink.models;

import com.mindlink.Util.DoublyLinkedList.DoublyLinkedList;
import lombok.Data;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

@Data
public class Student {
    Integer id;
    String name;
    String email;
    String password;
    List<String> interests;
    DoublyLinkedList<Content> publishedContents;
    DoublyLinkedList<Valoration> valorations;
    PriorityQueue<HelpRequest> helpRequests; // check in the future.

    public Student(Integer id, String name, String email, String password) {
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
