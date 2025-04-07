package com.mindlink.Util.DoublyLinkedList;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Node <E>{
    private Node<E> nextNode, previousNode;
    private E value;

    public Node(E value) {
        this.nextNode = null;
        this.previousNode = null;
        this.value = value;
    }

}
