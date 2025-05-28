package com.mindlink.Util.BinarySearchTree;

import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree<T extends Comparable<T>> {

    private Node<T> root;

    public BinarySearchTree() {
        root = null;
    }

    public void insert(T value) {
        root = insertRec(root, value);
    }

    private Node<T> insertRec(Node<T> root, T value) {
        if (root == null) {
            return new Node<>(value);
        }

        if (value.compareTo(root.value) < 0)
            root.left = insertRec(root.left, value);
        else if (value.compareTo(root.value) > 0)
            root.right = insertRec(root.right, value);

        return root;
    }

    public boolean search(T value) {
        return searchRec(root, value);
    }

    private boolean searchRec(Node<T> root, T value) {
        if (root == null)
            return false;
        if (value.compareTo(root.value) == 0)
            return true;
        if (value.compareTo(root.value) < 0)
            return searchRec(root.left, value);
        return searchRec(root.right, value);
    }

    public List<T> toSortedList() {
        List<T> result = new ArrayList<>();
        inorderRec(root, result);
        return result;
    }

    private void inorderRec(Node<T> node, List<T> list) {
        if (node != null) {
            inorderRec(node.left, list);
            list.add(node.value);
            inorderRec(node.right, list);
        }
    }
}
