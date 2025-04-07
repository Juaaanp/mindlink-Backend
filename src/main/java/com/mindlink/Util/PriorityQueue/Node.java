package com.mindlink.Util.PriorityQueue;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Node <E>{
    private Node<E> nextNode;
    private E value;

    public Node(E value) {
        this.nextNode = null;
        this.value = value;
    }
}
