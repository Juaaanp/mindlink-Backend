package com.mindlink.Util.BinarySearchTree;

import lombok.Data;

@Data
public class Node <T> {
    
    T value;
    Node<T> left, right;

    public Node(T value) {
        this.value = value;
        left = right = null;
    }
}
