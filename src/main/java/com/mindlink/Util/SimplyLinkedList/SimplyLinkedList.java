package com.mindlink.Util.SimplyLinkedList;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class SimplyLinkedList<T> {

    private Node<T> initial;
    private int size = 0;

    public List<T> toList() {
        List<T> result = new ArrayList<>();
        Node<T> current = initial;
        while (current != null) {
            result.add(current.getValue());
            current = current.getNext();
        }
        return result;
    }

    public static <T> SimplyLinkedList<T> fromList(List<T> list) {
        SimplyLinkedList<T> linkedList = new SimplyLinkedList<>();
        for (T item : list) {
            linkedList.addLast(item);
        }
        return linkedList;
    }

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

    // Delete()?
}
