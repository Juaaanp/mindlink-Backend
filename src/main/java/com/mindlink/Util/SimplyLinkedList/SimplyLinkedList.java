package com.mindlink.Util.SimplyLinkedList;

import lombok.Data;

@Data
public class SimplyLinkedList<T> {
    
    private Node<T> initial;
    private int size = 0;

    public void addLast(T value) {
        Node<T> newNode = new Node<>(value);
        if (initial == null) {
            initial = newNode;
        } else {
            Node<T> curr = initial;
            while (curr.getNext() != null) {
                curr = curr.getNext();
            }
            curr.setNext(newNode);
        }
        size++;
    }

    public void addFirst(T value) {
        Node<T> newNode = new Node<>(value);
        newNode.setNext(initial);
        initial = newNode;
        size++;
    }

    //Delete()?
}
