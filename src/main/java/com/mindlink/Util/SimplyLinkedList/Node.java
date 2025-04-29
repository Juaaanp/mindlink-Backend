package com.mindlink.Util.SimplyLinkedList;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Node<T> {
    
    private T value;
    private Node<T> next;

    public Node(T value) {
        this.value = value;
        this.next = null;
    }

}
